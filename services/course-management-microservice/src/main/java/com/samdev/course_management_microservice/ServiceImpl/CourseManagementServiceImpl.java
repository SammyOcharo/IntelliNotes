package com.samdev.course_management_microservice.ServiceImpl;

import com.samdev.course_management_microservice.AWS.AwsUtil;
import com.samdev.course_management_microservice.ContentGenOpenFeign.ConfirmStudentInfo;
import com.samdev.course_management_microservice.ContentGenOpenFeign.PdfReceiverClient;
import com.samdev.course_management_microservice.ContentGenOpenFeign.StudentOpenFeignResponse;
import com.samdev.course_management_microservice.Entity.Course;
import com.samdev.course_management_microservice.Exceptions.*;
import com.samdev.course_management_microservice.Mapper.CourseMapper;
import com.samdev.course_management_microservice.Repository.CourseManagementRepository;
import com.samdev.course_management_microservice.Repository.UnitRepository;
import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Request.UnitRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import com.samdev.course_management_microservice.Service.CourseManagementService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CourseManagementServiceImpl implements CourseManagementService {

    private static final Logger log = LoggerFactory.getLogger(CourseManagementServiceImpl.class);
    private final CourseManagementRepository courseManagementRepository;
    private final CourseMapper mapper;
    private final UnitRepository unitRepository;
    private final AwsUtil awsUtil;
    private final PdfReceiverClient pdfReceiverClient;
    private final ConfirmStudentInfo confirmStudentInfo;

    public CourseManagementServiceImpl(CourseManagementRepository courseManagementRepository, CourseMapper mapper, UnitRepository unitRepository, AwsUtil awsUtil, PdfReceiverClient pdfReceiverClient, ConfirmStudentInfo confirmStudentInfo) {
        this.courseManagementRepository = courseManagementRepository;
        this.mapper = mapper;
        this.unitRepository = unitRepository;
        this.awsUtil = awsUtil;
        this.pdfReceiverClient = pdfReceiverClient;
        this.confirmStudentInfo = confirmStudentInfo;
    }

    CourseResponse courseResponse = new CourseResponse();

    @Override
    public CourseResponse registerCourse(CourseRequest courseRequest) {
        log.info("This is the course request object, {}", courseRequest);
        if(courseManagementRepository.existsByCourseName(courseRequest.getCourseName()))
            throw new CourseAlreadyExistsException("Course already exists");
        if(courseRequest.getCourseName() == null)
            throw new InvalidCourseEntryException("Invalid course entered");

        log.info("This is the course request object, {}", courseRequest.getCourseName());

        //todo send a post request to student microservice to check whether the student exists
        try{
            confirmStudentInfo.findStudentById(courseRequest.getStudentId());

        }catch (StudentDoesNotExistException e){
            throw new StudentDoesNotExistException("Student does not exist!");
        }catch (Exception e){
            courseResponse.setStatusCode(500);
            courseResponse.setStatusMessage("An error has occurred!");

            return courseResponse;
        }

        courseManagementRepository.save(mapper.toCourse(courseRequest));
        courseResponse.setStatusCode(200);
        courseResponse.setStatusMessage("Course registered successfully!");
        courseResponse.setCourseRequest(courseRequest);


        return courseResponse;
    }

    @Override
    public List<CourseResponse> AllCourse() {
        return courseManagementRepository.findAll()
                .stream()
                .map(mapper::toCourseMapper)
                .toList();
    }

    @Override
    public List<CourseResponse> listAllUnits() {
        return unitRepository.findAll()
                .stream()
                .map(mapper::toUnitMapper)
                .toList();
    }

    @Override
    public CourseResponse registerUnit(String unitCode, String unitName, Long courseId, MultipartFile courseOutline) {
        log.info("This is the unit code: {}", unitCode);

        if (!courseManagementRepository.existsById(courseId)) {
            throw new CourseDoesNotExistException("Course selected does not exist!");
        }

        if (unitRepository.existsByUnitCode(unitCode)) {
            throw new UnitExistException("Unit already exists!");
        }

        // Save file to AWS S3
        String filePath = awsUtil.saveCourseToS3(courseOutline);

        // Send the file to content-generation microservice asynchronously
        pdfReceiverClient.uploadPdf(courseOutline);

        //send an async request with openFeign to content generation microservice
        CompletableFuture.runAsync(() ->{
                pdfReceiverClient.uploadPdf(courseOutline);
                System.out.println("The pdf has been sent to content generation microservice");
            }

        );

        // Proceed with business logic
        UnitRequest unitRequest = new UnitRequest();
        unitRequest.setUnitName(unitName);
        unitRequest.setUnitCode(unitCode);
        unitRequest.setCourseId(courseId);
        unitRequest.setOutline(filePath);

        Course course = courseManagementRepository.findById(courseId)
                .orElseThrow(() -> new CourseDoesNotExistException("Course selected does not exist!"));

        unitRepository.save(mapper.ToUnit(unitCode, unitName, courseId, filePath, course));

        // Generate presigned URL
        URL presignedUrl = awsUtil.generatePresignedUrl(filePath, 30);
        unitRequest.setOutline(presignedUrl.toString());

        // Prepare response
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setStatusMessage("Unit uploaded successfully!");
        courseResponse.setUnit(unitRequest);
        courseResponse.setStatusCode(200);

        return courseResponse;
    }

    @Override
    public CourseResponse getUnit(Long id) {

        if(id == null){
            throw new InvalidUnitIdException("Invalid unit id passed");
        }

        courseResponse = mapper.toUnitMapper(unitRepository.findById(id).get());
        courseResponse.setStatusCode(200);

        return courseResponse;
    }

    @Override
    public CourseResponse deleteUnit(Long id) {
        if(id == null)
            throw new InvalidUnitIdException("Invalid unit id passed");
        if(!unitRepository.existsById(id))
            throw new UnitDoesNotExistException("Unit does not exist");
        unitRepository.deleteById(id);
        courseResponse.setStatusMessage("Unit deleted successfully!");
        courseResponse.setStatusCode(200);
        return courseResponse;
    }

    @Transactional
    @Override
    public CourseResponse deleteMultipleUnits(Map<Integer, Integer> unitInfo) {
        if(unitInfo.isEmpty())
            throw new MapInformationException("No units in the map provided!");

        boolean allExist = unitInfo.values().stream()
                .allMatch(integer -> unitRepository.existsById(Long.valueOf(integer)));
        if(!allExist)
            throw new UnitDoesNotExistException("One or more passed units do not exist");

        boolean allDeleted = unitInfo.values()
                .stream()
                .map(integer -> {
                    try {
                        unitRepository.deleteById(Long.valueOf(integer));
                        return true;
                    } catch (UnitDeletionException e) {
                        return false;
                    }
                })
                .reduce((deletion1, deletion2) -> deletion1 && deletion2)
                .orElse(false);



        if (allDeleted){
            courseResponse.setStatusMessage("Selected units deleted successfully!");
            courseResponse.setStatusCode(200);
            return courseResponse;
        }else{
            courseResponse.setStatusMessage("Error occurred while deleting the units");
            courseResponse.setStatusCode(400);
            return courseResponse;
        }
    }

    @Override
    public CourseResponse deleteCourse(Long id) {
        if(id == null){
            throw new InvalidUnitIdException("Invalid unit id passed");
        }
        courseManagementRepository.deleteById(id);
        courseResponse.setStatusMessage("Course deleted successfully!");
        courseResponse.setStatusCode(200);
        return courseResponse;
    }

}

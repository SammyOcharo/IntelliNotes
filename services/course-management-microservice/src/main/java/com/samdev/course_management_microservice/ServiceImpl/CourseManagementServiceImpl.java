package com.samdev.course_management_microservice.ServiceImpl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.samdev.course_management_microservice.AWS.AwsUtil;
import com.samdev.course_management_microservice.Entity.Course;
import com.samdev.course_management_microservice.Exceptions.*;
import com.samdev.course_management_microservice.Mapper.CourseMapper;
import com.samdev.course_management_microservice.Repository.CourseManagementRepository;
import com.samdev.course_management_microservice.Repository.UnitRepository;
import com.samdev.course_management_microservice.Request.CourseRequest;
import com.samdev.course_management_microservice.Request.UnitRequest;
import com.samdev.course_management_microservice.Response.CourseResponse;
import com.samdev.course_management_microservice.Service.CourseManagementService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CourseManagementServiceImpl implements CourseManagementService {

    private static final Logger log = LoggerFactory.getLogger(CourseManagementServiceImpl.class);
    private final CourseManagementRepository courseManagementRepository;
    private final CourseMapper mapper;
    private final UnitRepository unitRepository;
    private final AwsUtil awsUtil;

    public CourseManagementServiceImpl(CourseManagementRepository courseManagementRepository, CourseMapper mapper, UnitRepository unitRepository, AwsUtil awsUtil) {
        this.courseManagementRepository = courseManagementRepository;
        this.mapper = mapper;
        this.unitRepository = unitRepository;
        this.awsUtil = awsUtil;
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
        log.info("This is the unit code: {}",unitCode);
        if(!courseManagementRepository.existsById(courseId))
            throw new CourseDoesNotExistException("Course selected does not exists!");

        if(unitRepository.existsByUnitCode(unitCode))
            throw new UnitExistException("Unit already exists!");

        //todo set up connection to AWS S3 to safe the course outline.
        String filePath = awsUtil.saveCourseToS3(courseOutline);

        UnitRequest unitRequest = new UnitRequest();
        unitRequest.setUnitName(unitName);
        unitRequest.setUnitCode(unitCode);
        unitRequest.setCourseId(courseId);
        unitRequest.setOutline(filePath);

        Course course = courseManagementRepository.findById(courseId).get();

        unitRepository.save(mapper.ToUnit(unitCode, unitName, courseId, filePath, course));

        //todo presign the aws s3 bucket for visibility.
        URL presignedUrl = awsUtil.generatePresignedUrl(filePath, 30);
        unitRequest.setOutline(presignedUrl.toString());


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
        if(id == null){
            throw new InvalidUnitIdException("Invalid unit id passed");
        }
        unitRepository.deleteById(id);
        courseResponse.setStatusMessage("Unit deleted successfully!");
        courseResponse.setStatusCode(200);
        return courseResponse;
    }

    @Override
    public CourseResponse deleteMultipleUnits(Map<Integer, Integer> unitInfo) {
        if(unitInfo.isEmpty())
            throw new MapInformationException("No units in the map provided!");

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

        return null;
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

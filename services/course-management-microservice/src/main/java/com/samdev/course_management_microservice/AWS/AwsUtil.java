package com.samdev.course_management_microservice.AWS;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.samdev.course_management_microservice.Exceptions.S3FileUploadException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class AwsUtil {

    @Value("${aws.s3.bucketName}")
    private String bucketName;
    @Value("${aws.s3.accessKey}")
    private String accessKey;
    @Value("${aws.s3.secretKey}")
    private String secretKey;

    private AmazonS3 s3Client;

    @PostConstruct
    private void initialize(){
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_WEST_1)
                .build();
    }

    public String saveCourseToS3(MultipartFile courseOutline){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        try{
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(courseOutline.getContentType());
            objectMetadata.setContentLength(courseOutline.getSize());
            String filePath = todayDate+"/"+courseOutline.getOriginalFilename();
            s3Client.putObject(bucketName, filePath, courseOutline.getInputStream(), objectMetadata);

            return filePath;


        }catch (IOException e) {
            throw new S3FileUploadException("Error saving file");
        }

    }

    public URL generatePresignedUrl(String key, int expirationTimeInMinutes){
        Date expiration = new Date();
        long expTimeMillis = System.currentTimeMillis() + (long) expirationTimeInMinutes * 60 * 1000;
        expiration.setTime(expTimeMillis);

        //Create pre-signed urls
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, key)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);

        return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }
}

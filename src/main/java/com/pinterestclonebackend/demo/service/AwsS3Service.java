package com.pinterestclonebackend.demo.service;

import com.pinterestclonebackend.demo.constants.ApiResponseCode;
import com.pinterestclonebackend.demo.dto.response.ContentAwsDTO;
import com.pinterestclonebackend.demo.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AwsS3Service {
    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.folderName}")
    private String folderName;

    public AwsS3Service() {
        this.s3Client = S3Client.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(() -> AwsBasicCredentials.create(this.accessKey, this.secretKey))
                .build();
    }

    public String uploadFile(MultipartFile multipartFile) {
        String contentSummary = this.getSizeOfFile(multipartFile.getSize());
        String contentTitle = multipartFile.getOriginalFilename();
        String contentSubType = contentTitle.substring(contentTitle.indexOf("."));
        String key = this.folderName + "/" + UUID.randomUUID() + contentSubType;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(this.bucketName)
                    .key(key)
                    .contentLength(multipartFile.getSize())
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getInputStream().readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ApiResponseCode.INTERNAL_SERVER_ERROR);
        }

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .build();
        URL url = s3Client.utilities().getUrl(getUrlRequest);

        return url.toString();
    }

    public byte[] getObject(String bucketName, String key) {
        GetObjectRequest objectRequest = GetObjectRequest
                .builder()
                .key(key)
                .bucket(bucketName)
                .build();
        ResponseInputStream<GetObjectResponse> res = s3Client.getObject(objectRequest);

        try {
            return res.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getImageURL(String bucketName, String key) {
        GetUrlRequest urlRequest = GetUrlRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .build();
        URL url = s3Client.utilities().getUrl(urlRequest);

        return url.toString();
    }

    public void deleteImagesByURL(String bucketName, List<String> keys) {
        List<ObjectIdentifier> objectsToDelete = new ArrayList<>();
        for (String key : keys) {
            objectsToDelete.add(ObjectIdentifier.builder().key(key).build());
        }

        DeleteObjectsRequest objectDeleteRequest = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(Delete.builder().objects(objectsToDelete).build())
                .build();

        DeleteObjectsResponse response = s3Client.deleteObjects(objectDeleteRequest);
        List<DeletedObject> deletedObjects = response.deleted();
        for (DeletedObject deletedObject : deletedObjects) {
            System.out.println("Deleted: " + deletedObject.key());
        }
    }

    public String getSizeOfFile(Long size) {
        String[] array = {"B", "KB", "MB", "GB"};
        int count = 0;
        for (count = 0; size > 1024.0; count++) {
            size = size / 1024;
        }
        return size + array[count];
    }

}

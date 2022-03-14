package com.demo.restapi.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.demo.restapi.payload.MediaUploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.demo.restapi.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOG = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.bucket.name}")
    private String s3BucketName;

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);
        return amazonS3.generatePresignedUrl(s3BucketName, fileName, calendar.getTime(), httpMethod).toString();
    }

    @Override
    public String findByName(String fileName) {
        if (!amazonS3.doesObjectExist(s3BucketName, fileName))
            return "File does not exist";
        LOG.info("Generating signed URL for file name {}", fileName);
        return generateUrl(fileName, HttpMethod.GET);
    }

    @Override
    public MediaUploadResponse generatePresignedUrl(String extension) {
        String fileName = UUID.randomUUID().toString() + '.' + extension;
        return new MediaUploadResponse(fileName, generateUrl(fileName, HttpMethod.PUT));
    }
}

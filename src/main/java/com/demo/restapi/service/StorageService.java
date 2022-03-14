package com.demo.restapi.service;

import com.demo.restapi.payload.MediaUploadResponse;
import org.springframework.scheduling.annotation.Async;

public interface StorageService {

    @Async
    String findByName(String fileName);

    @Async
    MediaUploadResponse generatePresignedUrl(String extension);
}

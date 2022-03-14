package com.demo.restapi.service;

import org.springframework.scheduling.annotation.Async;

public interface StorageService {

    @Async
    String findByName(String fileName);

    @Async
    String generatePresignedUrl(String extension);
}

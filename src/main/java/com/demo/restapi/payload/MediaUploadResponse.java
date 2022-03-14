package com.demo.restapi.payload;

import lombok.Data;

@Data
public class MediaUploadResponse {
    private String keyName;
    private String presignUrl;

    public MediaUploadResponse(String keyName, String presignUrl) {
        this.keyName = keyName;
        this.presignUrl = presignUrl;
    }
}

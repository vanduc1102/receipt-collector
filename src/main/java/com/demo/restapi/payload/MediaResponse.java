package com.demo.restapi.payload;

import lombok.Data;

@Data
public class MediaResponse {
    private Long id;
    private String fileName;
    private String url;
    private Long receiptId;

    public MediaResponse(Long id, String fileName, String url, Long receiptId) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
        this.receiptId = receiptId;
    }

}
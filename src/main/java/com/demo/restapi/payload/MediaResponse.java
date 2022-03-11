package com.demo.restapi.payload;

import lombok.Data;

@Data
public class MediaResponse {
    private Long id;
    private String url;
    private Long receiptId;

    public MediaResponse(Long id, String url, Long receiptId) {
        this.id = id;
        this.url = url;
        this.receiptId = receiptId;
    }

}
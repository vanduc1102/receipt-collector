package com.demo.restapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MediaRequest {

    @NotBlank
    private String fileName;

    @NotBlank
    private String keyName;

    private Long receiptId;
}
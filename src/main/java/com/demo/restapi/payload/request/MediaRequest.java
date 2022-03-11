package com.demo.restapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MediaRequest {

    @NotBlank
    @Size(min = 10)
    private String url;

    @NotNull
    private Long receiptId;
}
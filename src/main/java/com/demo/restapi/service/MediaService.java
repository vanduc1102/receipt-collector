package com.demo.restapi.service;

import com.demo.restapi.payload.ApiResponse;
import com.demo.restapi.payload.PagedResponse;
import com.demo.restapi.payload.request.MediaRequest;
import com.demo.restapi.payload.MediaResponse;
import com.demo.restapi.security.UserPrincipal;

public interface MediaService {

    PagedResponse<MediaResponse> getAllMedias(int page, int size);

    MediaResponse getMedia(Long id);

    MediaResponse updateMedia(Long id, MediaRequest mediaRequest, UserPrincipal currentUser);

    MediaResponse addMedia(MediaRequest mediaRequest, UserPrincipal currentUser);

    ApiResponse deleteMedia(Long id, UserPrincipal currentUser);

    PagedResponse<MediaResponse> getAllMediasByReceipt(Long receiptId, int page, int size);

}
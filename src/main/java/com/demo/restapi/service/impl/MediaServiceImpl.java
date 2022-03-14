package com.demo.restapi.service.impl;

import com.demo.restapi.exception.ResourceNotFoundException;
import com.demo.restapi.exception.UnauthorizedException;
import com.demo.restapi.model.Media;
import com.demo.restapi.model.Receipt;
import com.demo.restapi.payload.ApiResponse;
import com.demo.restapi.payload.MediaResponse;
import com.demo.restapi.payload.PagedResponse;
import com.demo.restapi.payload.request.MediaRequest;
import com.demo.restapi.repository.MediaRepository;
import com.demo.restapi.repository.ReceiptRepository;
import com.demo.restapi.security.UserPrincipal;
import com.demo.restapi.service.MediaService;
import com.demo.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.demo.restapi.utils.AppUtils;
import com.demo.restapi.utils.AppConstants;
import static com.demo.restapi.utils.AppConstants.ID;
import static com.demo.restapi.utils.AppConstants.MEDIA;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private StorageService storageService;

    @Override
    public PagedResponse<MediaResponse> getAllMedias(int page, int size) {
        return null;
    }

    @Override
    public MediaResponse getMedia(Long id) {
        return null;
    }

    @Override
    public MediaResponse updateMedia(Long id, MediaRequest mediaRequest, UserPrincipal currentUser) {
        return null;
    }

    @Override
    public MediaResponse addMedia(MediaRequest mediaRequest, UserPrincipal currentUser) {
        Receipt receipt = receiptRepository.findById(mediaRequest.getReceiptId())
                .orElseThrow(() -> new ResourceNotFoundException(MEDIA, ID, mediaRequest.getReceiptId()));
        if (receipt.getUser().getId().equals(currentUser.getId())) {
            Media media = new Media(mediaRequest.getFileName(), mediaRequest.getKeyName(), receipt);
            Media newMedia = mediaRepository.save(media);
            return new MediaResponse(newMedia.getId(), media.getKeyName(),
                    storageService.findByName(media.getKeyName()), newMedia.getReceipt().getId());
        }

        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to add photo in this receipt");

        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public ApiResponse deleteMedia(Long id, UserPrincipal currentUser) {
        return null;
    }

    @Override
    public PagedResponse<MediaResponse> getAllMediasByReceipt(Long receiptId, int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, AppConstants.CREATED_AT);

        Page<Media> medias = mediaRepository.findByReceiptId(receiptId, pageable);

        List<MediaResponse> photoResponses = new ArrayList<>(medias.getContent().size());
        for (Media media : medias.getContent()) {
            photoResponses.add(new MediaResponse(media.getId(), media.getKeyName(),
                    storageService.findByName(media.getKeyName()), media.getReceipt().getId()));
        }
        return new PagedResponse<>(photoResponses, medias.getNumber(), medias.getSize(), medias.getTotalElements(),
                medias.getTotalPages(), medias.isLast());
    }
}

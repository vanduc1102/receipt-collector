package com.demo.restapi.payload.request;

import com.demo.restapi.model.Media;
import com.demo.restapi.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ReceiptRequest {

    private Long id;

    private String title;

    private String description;

    private User user;

    private List<MediaRequest> medias;

    public List<MediaRequest> getMedias() {

        return medias == null ? null : new ArrayList<>(medias);
    }

    public void setMedias(List<MediaRequest> media) {

        if (media == null) {
            this.medias = null;
        } else {
            this.medias = Collections.unmodifiableList(media);
        }
    }
}

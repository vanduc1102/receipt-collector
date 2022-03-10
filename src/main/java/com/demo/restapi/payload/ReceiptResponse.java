package com.demo.restapi.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.demo.restapi.model.Media;
import com.demo.restapi.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(Include.NON_NULL)
public class ReceiptResponse {
    private Long id;

    private String title;

    private User user;

    private List<Media> media;

    public List<Media> getMedia() {

        return media == null ? null : new ArrayList<>(media);
    }

    public void setMedia(List<Media> media) {

        if (media == null) {
            this.media = null;
        } else {
            this.media = Collections.unmodifiableList(media);
        }
    }
}
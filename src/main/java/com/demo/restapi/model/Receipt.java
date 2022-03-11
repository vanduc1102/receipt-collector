package com.demo.restapi.model;

import com.demo.restapi.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
@Table(name="receipts")
@Data
public class Receipt extends UserDateAudit {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> media;

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public List<Media> getMedia() {
        return this.media == null ? null : new ArrayList<>(this.media);
    }

    public void setMedia(List<Media> media) {
        if (media == null) {
            this.media = null;
        } else {
            this.media = Collections.unmodifiableList(media);
        }
    }
}

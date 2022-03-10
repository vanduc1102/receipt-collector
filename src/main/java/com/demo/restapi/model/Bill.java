package com.demo.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.demo.restapi.model.audit.UserDateAudit;
import com.demo.restapi.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "bills")
public class Bill extends UserDateAudit {
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

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> media;

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public List<Media> getPhoto() {
        return this.media == null ? null : new ArrayList<>(this.media);
    }

    public void setPhoto(List<Media> media) {
        if (media == null) {
            this.media = null;
        } else {
            this.media = Collections.unmodifiableList(media);
        }
    }
}

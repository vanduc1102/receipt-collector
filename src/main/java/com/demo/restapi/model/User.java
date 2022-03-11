package com.demo.restapi.model;

import com.demo.restapi.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
        @UniqueConstraint(columnNames = { "email" }) })
@Data
public class User extends DateAudit {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(max = 40)
    private String lastName;

    @NotBlank
    @Column(name = "username")
    @Size(max = 15)
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @NotBlank
    @NaturalId
    @Size(max = 40)
    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone")
    private String phone;

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id)
                && email != null && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

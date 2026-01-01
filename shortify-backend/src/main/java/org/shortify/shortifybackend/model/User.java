package org.shortify.shortifybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {
    private String email;
    private String password;
    private String username;
    private String role = "ROLE_USER";
}

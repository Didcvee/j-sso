package ru.didcvee.sso.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    private UUID id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String secondName;
    private String middleName;
    private LocalDate birthday;
    private String avatarUrl;
    private Boolean active;
}

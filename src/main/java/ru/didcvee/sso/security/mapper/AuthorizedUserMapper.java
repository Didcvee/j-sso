package ru.didcvee.sso.security.mapper;

import lombok.experimental.UtilityClass;
import ru.didcvee.sso.entity.UserEntity;
import ru.didcvee.sso.security.entity.AuthorizedUser;

import java.security.AuthProvider;
import java.util.Collections;

@UtilityClass
public class AuthorizedUserMapper {

    public AuthorizedUser map(UserEntity entity, AuthProvider provider) {
        return AuthorizedUser.builder(entity.getEmail(), entity.getPasswordHash(), Collections.emptyList())
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .secondName(entity.getSecondName())
                .middleName(entity.getMiddleName())
                .birthday(entity.getBirthday())
                .avatarUrl(entity.getAvatarUrl())
                .build();
    }
}

package ru.didcvee.sso.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.didcvee.sso.entity.UserEntity;
import ru.didcvee.sso.repository.UserRepository;
import ru.didcvee.sso.security.mapper.AuthorizedUserMapper;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByEmail(username);
        if (entity == null) {
            throw new UsernameNotFoundException("User with username = " + username + " not found");
        }
        return AuthorizedUserMapper.map(entity, null);
    }
}


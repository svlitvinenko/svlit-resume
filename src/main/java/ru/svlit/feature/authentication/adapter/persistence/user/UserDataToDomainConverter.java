package ru.svlit.feature.authentication.adapter.persistence.user;

import org.springframework.stereotype.Service;
import ru.svlit.core.global.configuration.security.Role;
import ru.svlit.feature.authentication.domain.User;

import static java.util.stream.Collectors.toSet;

@Service
public class UserDataToDomainConverter {

    public User convert(UserModel userModel) {
        return new User(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getRoles().stream().map(this::toRole).collect(toSet()),
                userModel.isActive()
        );
    }

    private Role toRole(String roleString) {
        return Role.valueOf(roleString);
    }
}
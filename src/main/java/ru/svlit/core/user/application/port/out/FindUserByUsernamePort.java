package ru.svlit.core.user.application.port.out;

import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

public interface FindUserByUsernamePort {
    Optional<User> findByUsername(String username);
}

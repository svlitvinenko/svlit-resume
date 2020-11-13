package ru.svlit.feature.authentication.application.port.in;

import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

public interface FindUserByUsernameUseCase {
    Optional<User> findByUsername(String username);
}

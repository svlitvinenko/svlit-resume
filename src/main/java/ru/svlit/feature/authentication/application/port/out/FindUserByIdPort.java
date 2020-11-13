package ru.svlit.feature.authentication.application.port.out;

import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

public interface FindUserByIdPort {
    Optional<User> findById(String id);
}

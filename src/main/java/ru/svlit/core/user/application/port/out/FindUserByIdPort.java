package ru.svlit.core.user.application.port.out;

import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

public interface FindUserByIdPort {
    Optional<User> findById(String id);
}

package ru.svlit.core.user.application.port.in;

import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

public interface FindUserByIdUseCase {
    Optional<User> findByUd(String id);
}

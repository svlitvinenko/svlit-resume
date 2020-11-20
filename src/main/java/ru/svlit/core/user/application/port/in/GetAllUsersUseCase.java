package ru.svlit.core.user.application.port.in;

import ru.svlit.feature.authentication.domain.User;

public interface GetAllUsersUseCase {
    Iterable<User> getAllUsers();
}

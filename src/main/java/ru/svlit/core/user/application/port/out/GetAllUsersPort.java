package ru.svlit.core.user.application.port.out;

import ru.svlit.feature.authentication.domain.User;

public interface GetAllUsersPort {
    Iterable<User> getAllUsers();
}

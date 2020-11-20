package ru.svlit.core.user.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.user.application.port.in.GetAllUsersUseCase;
import ru.svlit.core.user.application.port.out.GetAllUsersPort;
import ru.svlit.feature.authentication.domain.User;

@UseCase
@RequiredArgsConstructor
class GetAllUsersService implements GetAllUsersUseCase {

    private final GetAllUsersPort getAllUsersPort;

    @Override
    public Iterable<User> getAllUsers() {
        return getAllUsersPort.getAllUsers();
    }
}

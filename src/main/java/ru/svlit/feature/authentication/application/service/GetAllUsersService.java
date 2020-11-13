package ru.svlit.feature.authentication.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.authentication.application.port.in.GetAllUsersUseCase;
import ru.svlit.feature.authentication.application.port.out.GetAllUsersPort;
import ru.svlit.feature.authentication.domain.User;

@UseCase
@RequiredArgsConstructor
public class GetAllUsersService implements GetAllUsersUseCase {

    private final GetAllUsersPort getAllUsersPort;

    @Override
    public Iterable<User> getAllUsers() {
        return getAllUsersPort.getAllUsers();
    }
}

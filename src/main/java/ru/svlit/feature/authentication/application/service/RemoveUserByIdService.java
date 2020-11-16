package ru.svlit.feature.authentication.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.authentication.application.port.in.RemoveUserByIdUseCase;
import ru.svlit.feature.authentication.application.port.out.RemoveUserByIdPort;

@UseCase
@RequiredArgsConstructor
class RemoveUserByIdService implements RemoveUserByIdUseCase {

    private final RemoveUserByIdPort removeUserByIdPort;

    @Override
    public void removeById(String id) {
        removeUserByIdPort.removeById(id);
    }
}

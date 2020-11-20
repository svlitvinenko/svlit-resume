package ru.svlit.core.user.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.user.application.port.in.RemoveUserByIdUseCase;
import ru.svlit.core.user.application.port.out.RemoveUserByIdPort;

@UseCase
@RequiredArgsConstructor
class RemoveUserByIdService implements RemoveUserByIdUseCase {

    private final RemoveUserByIdPort removeUserByIdPort;

    @Override
    public void removeById(String id) {
        removeUserByIdPort.removeById(id);
    }
}

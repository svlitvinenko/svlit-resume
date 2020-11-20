package ru.svlit.core.user.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.user.application.port.in.FindUserByIdUseCase;
import ru.svlit.core.user.application.port.in.UpdateUserUseCase;
import ru.svlit.core.user.application.port.out.UpdateUserPort;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase.UserNotFoundException;

@UseCase
@RequiredArgsConstructor
class UpdateUserService implements UpdateUserUseCase {

    private final FindUserByIdUseCase findUserByIdUseCase;
    private final UpdateUserPort updateUserPort;

    @Override
    public void updateUser(UpdateUserCommand command) throws UserNotFoundException {
        final User oldUser = findUserByIdUseCase.findByUd(command.getId()).orElseThrow(UserNotFoundException::new);
        final User newUser = new User(
                command.getId(),
                command.getUsername(),
                command.getEmail(),
                oldUser.getPassword(),
                command.getRoles(),
                oldUser.isActive(),
                command.getSelectedLocale()
        );

        updateUserPort.update(newUser);
    }
}

package ru.svlit.feature.authentication.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.authentication.application.port.in.SignUpUseCase;
import ru.svlit.feature.authentication.application.port.out.FindUserByUsernamePort;
import ru.svlit.feature.authentication.application.port.out.SignUpPort;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

import static java.util.Collections.singleton;
import static java.util.UUID.randomUUID;
import static ru.svlit.core.global.configuration.security.Role.USER;

@UseCase
@RequiredArgsConstructor
class SignUpService implements SignUpUseCase {

    private final SignUpPort signUpPort;
    private final FindUserByUsernamePort findUserByUsernamePort;

    @Override
    public void signUp(SignUpCommand command) throws UsernameTakenException {
        final Optional<User> existedUserOptional = findUserByUsernamePort.findByUsername(command.getUsername());
        if (existedUserOptional.isPresent()) {
            throw new UsernameTakenException();
        }

        final User userToSave = createDefaultUser(command.getUsername(), command.getPassword());

        signUpPort.signUp(userToSave);
    }

    private User createDefaultUser(String username, String password) {
        return new User(
                randomUUID().toString(),
                username,
                password,
                singleton(USER),
                true
        );
    }
}

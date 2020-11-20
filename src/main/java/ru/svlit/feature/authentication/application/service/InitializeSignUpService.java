package ru.svlit.feature.authentication.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.user.application.port.out.FindUserByUsernamePort;
import ru.svlit.core.user.application.port.out.StoreUserPort;
import ru.svlit.feature.authentication.application.port.in.InitializeSignUpUseCase;
import ru.svlit.feature.authentication.application.port.out.SendActivationCodePort;
import ru.svlit.feature.authentication.application.port.out.SendActivationCodePort.ActivationCommand;
import ru.svlit.feature.authentication.application.port.out.StoreActivationCodePort;
import ru.svlit.feature.authentication.domain.ActivationCode;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

import static java.util.Collections.singleton;
import static java.util.UUID.randomUUID;
import static ru.svlit.core.global.configuration.security.Role.USER;
import static ru.svlit.core.globalization.domain.SupportedLocale.getDefaultLocale;

@UseCase
@RequiredArgsConstructor
class InitializeSignUpService implements InitializeSignUpUseCase {

    private final StoreUserPort storeUserPort;
    private final StoreActivationCodePort storeActivationCodePort;
    private final FindUserByUsernamePort findUserByUsernamePort;
    private final SendActivationCodePort sendActivationCodePort;

    @Override
    public void signUp(InitializeSignUpCommand command) throws UsernameTakenException {
        final Optional<User> existedUserOptional = findUserByUsernamePort.findByUsername(command.getUsername());
        if (existedUserOptional.isPresent()) {
            throw new UsernameTakenException();
        }

        final User userToSave = createDefaultUser(command.getUsername(), command.getPassword(), command.getEmail());

        storeUserPort.store(userToSave);

        final String activationCodeString = randomUUID().toString();
        final ActivationCode activationCode = new ActivationCode(activationCodeString, userToSave.getId());
        storeActivationCodePort.store(activationCode);
        sendActivationCodePort.send(new ActivationCommand(
                userToSave.getEmail(),
                userToSave.getUsername(),
                activationCode
        ));
    }

    private User createDefaultUser(String username, String password, String email) {
        return new User(
                randomUUID().toString(),
                username,
                email,
                password,
                singleton(USER),
                false,
                getDefaultLocale()
        );
    }
}

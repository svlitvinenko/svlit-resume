package ru.svlit.feature.authentication.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.authentication.application.port.in.ConfirmSignUpUseCase;
import ru.svlit.core.user.application.port.in.FindUserByIdUseCase;
import ru.svlit.feature.authentication.application.port.out.ActivateUserPort;
import ru.svlit.feature.authentication.application.port.out.FindActivationCodePort;
import ru.svlit.feature.authentication.application.port.out.RemoveActivationCodePort;
import ru.svlit.feature.authentication.domain.ActivationCode;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

/**
 * Сервис подтверждения регистрации.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@UseCase
@RequiredArgsConstructor
class ConfirmSignUpService implements ConfirmSignUpUseCase {

    private final FindActivationCodePort findActivationCodePort;
    private final RemoveActivationCodePort removeActivationCodePort;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final ActivateUserPort activateUserPort;

    @Override
    public void confirm(ConfirmSignUpCommand command) throws ActivationCodeNotFoundException {
        final Optional<ActivationCode> activationCode = findActivationCodePort.findActivationCode(command.getActivationCode());
        if (activationCode.isPresent()) {
            activateUser(activationCode.get().getUserId());
            removeActivationCode(activationCode.get());
        } else {
            throw new ActivationCodeNotFoundException();
        }
    }

    private void activateUser(String userId) {
        final Optional<User> userOptional = findUserByIdUseCase.findByUd(userId);
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            if (!user.isActive()) {
                activateUserPort.activate(user);
            }
        }
    }

    private void removeActivationCode(ActivationCode activationCode) {
        removeActivationCodePort.remove(activationCode);
    }
}

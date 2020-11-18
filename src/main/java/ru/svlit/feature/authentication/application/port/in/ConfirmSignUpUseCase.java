package ru.svlit.feature.authentication.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Прецедент использования "Подтверждение регистрации".
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
public interface ConfirmSignUpUseCase {

    void confirm(ConfirmSignUpCommand command) throws ActivationCodeNotFoundException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class ConfirmSignUpCommand {
        private final String activationCode;
    }

    class ActivationCodeNotFoundException extends Exception {
    }
}

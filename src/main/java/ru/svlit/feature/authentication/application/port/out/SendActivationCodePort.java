package ru.svlit.feature.authentication.application.port.out;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.feature.authentication.domain.ActivationCode;

/**
 * Порт отправки кода активации на почту клиента.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
public interface SendActivationCodePort {

    void send(ActivationCommand command);

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class ActivationCommand {
        private final String emailTo;
        private final String username;
        private final ActivationCode activationCode;
    }
}

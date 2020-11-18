package ru.svlit.feature.authentication.application.port.out;

import ru.svlit.feature.authentication.domain.ActivationCode;

import java.util.Optional;

/**
 * Порт поиска кода активации.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
public interface FindActivationCodePort {
    Optional<ActivationCode> findActivationCode(String activationCode);
}

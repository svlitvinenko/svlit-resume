package ru.svlit.feature.authentication.application.port.out;

import ru.svlit.feature.authentication.domain.ActivationCode;

/**
 * Порт сохранения кода активации.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
public interface StoreActivationCodePort {
    void store(ActivationCode activationCode);
}

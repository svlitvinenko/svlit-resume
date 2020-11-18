package ru.svlit.feature.authentication.application.port.out;

import ru.svlit.feature.authentication.domain.User;

/**
 * Порт активации аккаунта.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
public interface ActivateUserPort {
    void activate(User user);
}

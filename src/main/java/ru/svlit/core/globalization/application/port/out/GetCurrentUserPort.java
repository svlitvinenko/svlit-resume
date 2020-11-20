package ru.svlit.core.globalization.application.port.out;

import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

/**
 * Порт для получения текущего пользователя.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
public interface GetCurrentUserPort {
    Optional<User> getCurrentUser();
}

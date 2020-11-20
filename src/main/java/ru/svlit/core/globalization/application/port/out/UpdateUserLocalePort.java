package ru.svlit.core.globalization.application.port.out;

import ru.svlit.core.globalization.domain.SupportedLocale;

/**
 * Порт для обновления текущей локали пользователя.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
public interface UpdateUserLocalePort {
    void update(String userId, SupportedLocale locale);
}

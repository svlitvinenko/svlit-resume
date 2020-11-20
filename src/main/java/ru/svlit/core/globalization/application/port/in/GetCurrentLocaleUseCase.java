package ru.svlit.core.globalization.application.port.in;

import ru.svlit.core.globalization.domain.SupportedLocale;

/**
 * Прецедент использования — получение текущей локали пользователя.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
public interface GetCurrentLocaleUseCase {
    SupportedLocale getLocale();
}

package ru.svlit.core.globalization.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.core.globalization.domain.SupportedLocale;

/**
 * Прецедент использования — обновление текущей локали пользователя.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
public interface UpdateLocaleUseCase {
    void updateLocale(LocaleCommand command);

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class LocaleCommand {
        private final SupportedLocale locale;
    }
}

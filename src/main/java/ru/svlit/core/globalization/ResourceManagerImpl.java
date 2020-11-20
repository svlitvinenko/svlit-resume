package ru.svlit.core.globalization;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.svlit.core.globalization.application.port.in.GetCurrentLocaleUseCase;
import ru.svlit.core.globalization.domain.SupportedLocale;

import java.util.Locale;

/**
 * Реализация менеджера ресурсов.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
@Component
@RequiredArgsConstructor
class ResourceManagerImpl implements ResourceManager {

    private final MessageSource messageSource;
    private final GetCurrentLocaleUseCase getCurrentLocaleUseCase;

    @Override
    public String getText(Resource resource, Locale locale, Object[] args) {
        String code = resource.getCode();
        return messageSource.getMessage(code, args, locale);
    }

    @Override
    public String getText(Resource resource, Object[] args) {
        final Locale locale = mapLocale(getCurrentLocaleUseCase.getLocale());
        return getText(resource, locale, args);
    }

    private Locale mapLocale(SupportedLocale locale) {
        final String languageTag = switch (locale) {
            case EN -> "en-US";
            case RU -> "ru-RU";
        };

        return Locale.forLanguageTag(languageTag);
    }

    @Override
    public String getText(Resource resource) {
        return getText(resource, null);
    }
}

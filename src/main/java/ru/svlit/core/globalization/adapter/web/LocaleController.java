package ru.svlit.core.globalization.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.globalization.application.port.in.UpdateLocaleUseCase;
import ru.svlit.core.globalization.application.port.in.UpdateLocaleUseCase.LocaleCommand;
import ru.svlit.core.globalization.domain.SupportedLocale;
import ru.svlit.core.util.Redirect;

import java.util.Arrays;
import java.util.Optional;

/**
 * Контроллер локали.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
@WebAdapter
@RequestMapping("/locale")
@RequiredArgsConstructor
class LocaleController {

    private final UpdateLocaleUseCase updateLocaleUseCase;

    @GetMapping("/{tag}")
    public String update(@PathVariable String tag, @RequestParam(required = false) String returnTo) {
        final Optional<SupportedLocale> locale = parseLocale(tag);
        locale.ifPresent(supportedLocale -> updateLocaleUseCase.updateLocale(new LocaleCommand(supportedLocale)));

        return Redirect.to(returnTo.isBlank() ? "/" : returnTo);
    }

    private Optional<SupportedLocale> parseLocale(String tag) {
        return Arrays.stream(SupportedLocale.values()).filter(locale -> locale.name().equalsIgnoreCase(tag)).findFirst();
    }
}

package ru.svlit.core.globalization.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.globalization.application.port.in.GetCurrentLocaleUseCase;
import ru.svlit.core.globalization.application.port.in.UpdateLocaleUseCase;
import ru.svlit.core.globalization.application.port.out.GetCurrentUserPort;
import ru.svlit.core.globalization.application.port.out.UpdateUserLocalePort;
import ru.svlit.core.globalization.domain.SupportedLocale;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

import static ru.svlit.core.globalization.domain.SupportedLocale.getDefaultLocale;

/**
 * Сервис для работы с локалями пользователя.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
@UseCase
@RequiredArgsConstructor
public class LocaleService implements UpdateLocaleUseCase, GetCurrentLocaleUseCase {

    private final GetCurrentUserPort getCurrentUserPort;
    private final UpdateUserLocalePort updateUserLocalePort;

    @Override
    public void updateLocale(LocaleCommand command) {
        final Optional<User> currentUser = getCurrentUserPort.getCurrentUser();
        currentUser.ifPresent(user -> updateUserLocalePort.update(user.getId(), command.getLocale()));
    }

    @Override
    public SupportedLocale getLocale() {
        return getCurrentUserPort.getCurrentUser().stream()
                .map(User::getSelectedLocale).findFirst()
                .orElse(getDefaultLocale());
    }
}

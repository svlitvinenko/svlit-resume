package ru.svlit.core.globalization.adapter.local;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.LocalAdapter;
import ru.svlit.core.globalization.application.port.out.GetCurrentUserPort;
import ru.svlit.core.user.application.port.in.GetCurrentUserUseCase;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

/**
 * Адаптер для получения сведений о текущем пользователе.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
@LocalAdapter
@RequiredArgsConstructor
public class GetCurrentUserAdapter implements GetCurrentUserPort {

    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Override
    public Optional<User> getCurrentUser() {
        return getCurrentUserUseCase.getCurrentUser();
    }
}

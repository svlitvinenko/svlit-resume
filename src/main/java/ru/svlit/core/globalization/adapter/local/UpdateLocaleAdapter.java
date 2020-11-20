package ru.svlit.core.globalization.adapter.local;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.svlit.architecture.annotation.LocalAdapter;
import ru.svlit.core.globalization.application.port.out.UpdateUserLocalePort;
import ru.svlit.core.globalization.domain.SupportedLocale;
import ru.svlit.core.user.application.port.in.FindUserByIdUseCase;
import ru.svlit.core.user.application.port.in.UpdateUserUseCase;
import ru.svlit.core.user.application.port.in.UpdateUserUseCase.UpdateUserCommand;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase.UserNotFoundException;

import java.util.Optional;

/**
 * Адаптер для обновления локализации для пользователя.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
@Slf4j
@LocalAdapter
@RequiredArgsConstructor
public class UpdateLocaleAdapter implements UpdateUserLocalePort {

    private final UpdateUserUseCase updateUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;

    @Override
    public void update(String userId, SupportedLocale locale) {
        final Optional<User> userOptional = findUserByIdUseCase.findByUd(userId);
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            try {
                updateUserUseCase.updateUser(new UpdateUserCommand(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles(),
                        locale
                ));
            } catch (UserNotFoundException e) {
                log.error("User with id " + userId + " was not found.");
            }
        }
    }
}

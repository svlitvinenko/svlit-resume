package ru.svlit.core.user.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.core.global.configuration.security.Role;
import ru.svlit.core.globalization.domain.SupportedLocale;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase;

import java.util.Set;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserCommand command) throws SubmitMessageUseCase.UserNotFoundException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class UpdateUserCommand {
        private final String id;
        private final String username;
        private final String email;
        private final Set<Role> roles;
        private final SupportedLocale selectedLocale;
    }
}

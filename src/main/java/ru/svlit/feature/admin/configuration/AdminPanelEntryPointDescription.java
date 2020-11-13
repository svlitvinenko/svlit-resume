package ru.svlit.feature.admin.configuration;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.EntryPoint;
import ru.svlit.feature.authentication.application.port.in.GetCurrentUserUseCase;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

import static ru.svlit.feature.authentication.domain.Role.ADMIN;

@EntryPoint
@RequiredArgsConstructor
public class AdminPanelEntryPointDescription implements EntryPointDescription {

    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Override
    public boolean isEnabled() {
        final Optional<User> currentUser = getCurrentUserUseCase.getCurrentUser();
        if (currentUser.isEmpty()) {
            return false;
        } else {
            return currentUser.get().getRoles().contains(ADMIN);
        }
    }

    @Override
    public String getTitle() {
        return "Admin Panel";
    }

    @Override
    public String getRelativeAddress() {
        return "/user";
    }
}

package ru.svlit.feature.admin.configuration;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.EntryPoint;
import ru.svlit.core.globalization.Resource;
import ru.svlit.core.globalization.Resource.FeatureAdminPanelTitleResource;
import ru.svlit.core.user.application.port.in.GetCurrentUserUseCase;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

import static ru.svlit.core.global.configuration.security.Role.ADMIN;
import static ru.svlit.feature.admin.configuration.AdminPanelConfigurationConstants.PATH_ADMIN_PANEL;

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
    public Resource getTitle() {
        return new FeatureAdminPanelTitleResource();
    }

    @Override
    public String getRelativeAddress() {
        return PATH_ADMIN_PANEL;
    }
}

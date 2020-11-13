package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.util.*;
import ru.svlit.feature.authentication.application.port.in.GetCurrentUserUseCase;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.home.application.port.in.GetContactsUseCase;
import ru.svlit.feature.home.application.port.in.GetEntryPointsUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
class GetNavigationContentService implements GetNavigationContentUseCase {

    private final GetEntryPointsUseCase getEntryPointsUseCase;
    private final GetContactsUseCase getContactsUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Override
    public NavigationContent perform(GetNavigationContentCommand command) {
        final UserInfo userInfo;

        if (!command.isSigningActionsVisible()) {
            userInfo = new IgnoringUserInfo();
        } else {
            final Optional<User> currentUser = getCurrentUserUseCase.getCurrentUser();
            if (currentUser.isPresent()) {
                userInfo = new SignedInUserInfo(currentUser.get());
            } else {
                userInfo = new GuestUserInfo();
            }
        }

        return new NavigationContent(
                getEntryPointsUseCase.perform(),
                getContactsUseCase.perform(),
                userInfo
        );
    }
}

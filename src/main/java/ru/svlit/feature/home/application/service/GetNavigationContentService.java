package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.util.GuestUserInfo;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.SignedInUserInfo;
import ru.svlit.feature.home.application.port.in.GetContactsUseCase;
import ru.svlit.feature.home.application.port.in.GetEntryPointsUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;

@UseCase
@RequiredArgsConstructor
class GetNavigationContentService implements GetNavigationContentUseCase {

    private final GetEntryPointsUseCase getEntryPointsUseCase;
    private final GetContactsUseCase getContactsUseCase;

    @Override
    public NavigationContent perform() {
        return new NavigationContent(
                getEntryPointsUseCase.perform(),
                getContactsUseCase.perform(),
                new SignedInUserInfo("test_username")
        );
    }
}

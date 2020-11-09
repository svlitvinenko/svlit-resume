package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.adapter.web.UnifiedModelAndView.NavigationContent;
import ru.svlit.feature.home.application.port.in.GetContactsUseCase;
import ru.svlit.feature.home.application.port.in.GetEntryPointsUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;

@UseCase
@RequiredArgsConstructor
public class GetNavigationContentService implements GetNavigationContentUseCase {

    private final GetEntryPointsUseCase getEntryPointsUseCase;
    private final GetContactsUseCase getContactsUseCase;

    @Override
    public NavigationContent perform() {
        return new NavigationContent(
                getEntryPointsUseCase.perform(),
                getContactsUseCase.perform()
        );
    }
}

package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.globalization.Resource.*;
import ru.svlit.core.globalization.ResourceManager;
import ru.svlit.core.globalization.domain.SupportedLocale;
import ru.svlit.core.user.application.port.in.GetCurrentUserUseCase;
import ru.svlit.core.util.*;
import ru.svlit.core.util.NavigationContent.FooterContent;
import ru.svlit.core.util.NavigationContent.FooterContent.LocaleDescription;
import ru.svlit.core.util.NavigationContent.HeaderContent;
import ru.svlit.core.util.NavigationContent.HeaderContent.EntryPoint;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.home.application.port.in.GetContactsUseCase;
import ru.svlit.feature.home.application.port.in.GetEntryPointsUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static ru.svlit.core.globalization.domain.SupportedLocale.getDefaultLocale;
import static ru.svlit.core.globalization.domain.SupportedLocale.values;

@UseCase
@RequiredArgsConstructor
class GetNavigationContentService implements GetNavigationContentUseCase {

    private static final String BASE_PATH = "/locale/";

    private final GetEntryPointsUseCase getEntryPointsUseCase;
    private final GetContactsUseCase getContactsUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final ResourceManager resourceManager;

    @Override
    public NavigationContent perform(GetNavigationContentCommand command) {
        final UserInfo userInfo;
        LocaleDescription selectedLocale = getLocaleDescription(getDefaultLocale());

        if (!command.isSigningActionsVisible()) {
            userInfo = new IgnoringUserInfo();
        } else {
            final Optional<User> currentUser = getCurrentUserUseCase.getCurrentUser();
            if (currentUser.isPresent()) {
                userInfo = new SignedInUserInfo(currentUser.get());
                selectedLocale = getLocaleDescription(currentUser.get().getSelectedLocale());
            } else {
                userInfo = new GuestUserInfo();
            }
        }

        final List<LocaleDescription> availableLocales = new ArrayList<>(
                asList(values())).stream().map(this::getLocaleDescription).collect(toList()
        );
        availableLocales.remove(selectedLocale);

        return new NavigationContent(
                new HeaderContent(
                        resourceManager.getText(new TitleResource()),
                        resourceManager.getText(new FeaturesResource()),
                        resourceManager.getText(new ContactsResource()),
                        resourceManager.getText(new SignInResource()),
                        resourceManager.getText(new SignOutResource()),
                        resourceManager.getText(new SignUpResource()),
                        getEntryPointsUseCase.perform().stream().map(this::convertToEntryPoint).collect(toList()),
                        getContactsUseCase.perform(),
                        userInfo
                ),
                new FooterContent(
                        selectedLocale,
                        unmodifiableList(availableLocales)
                )
        );
    }

    private EntryPoint convertToEntryPoint(EntryPointDescription entryPointDescription) {
        return new EntryPoint(
                entryPointDescription.getRelativeAddress(),
                resourceManager.getText(entryPointDescription.getTitle())
        );
    }

    private LocaleDescription getLocaleDescription(SupportedLocale locale) {
        final String link = BASE_PATH + locale.name().toLowerCase();
        final String name = switch (locale) {
            case EN -> resourceManager.getText(new LanguageEnglishResource());
            case RU -> resourceManager.getText(new LanguageRussianResource());
        };

        return new LocaleDescription(link, name);
    }
}

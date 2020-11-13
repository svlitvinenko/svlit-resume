package ru.svlit.feature.home.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.core.util.NavigationContent;

public interface GetNavigationContentUseCase {
    NavigationContent perform(GetNavigationContentCommand command);

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class GetNavigationContentCommand {
        final boolean isSigningActionsVisible;
    }
}

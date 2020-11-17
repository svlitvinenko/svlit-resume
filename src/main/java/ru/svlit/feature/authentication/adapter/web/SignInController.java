package ru.svlit.feature.authentication.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.UnifiedModelAndView;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase.GetNavigationContentCommand;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_SIGN_IN;
import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.SEGMENT_SIGN_IN;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping(PATH_SIGN_IN)
class SignInController {

    private final GetNavigationContentUseCase getNavigationContentUseCase;

    @GetMapping
    public UnifiedModelAndView getSignInForm() {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(false)
        );

        return new UnifiedModelAndView(SEGMENT_SIGN_IN, navigationContent);
    }
}

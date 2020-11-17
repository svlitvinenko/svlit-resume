package ru.svlit.feature.authentication.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.Redirect;
import ru.svlit.core.util.UnifiedModelAndView;
import ru.svlit.feature.authentication.application.port.in.SignUpUseCase;
import ru.svlit.feature.authentication.application.port.in.SignUpUseCase.SignUpCommand;
import ru.svlit.feature.authentication.application.port.in.SignUpUseCase.UsernameTakenException;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase.GetNavigationContentCommand;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.*;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping(PATH_SIGN_UP)
class SignUpController {

    private final SignUpUseCase signUpUseCase;
    private final GetNavigationContentUseCase getNavigationContentUseCase;

    @GetMapping
    public UnifiedModelAndView getSignUpForm() {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(false)
        );
        return new UnifiedModelAndView(SEGMENT_SIGN_UP, navigationContent);
    }

    @PostMapping
    public ModelAndView signUpWithCredentials(final String username, final String password) {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(false)
        );
        final SignUpCommand command = new SignUpCommand(username, password);

        try {
            signUpUseCase.signUp(command);
            return new UnifiedModelAndView(Redirect.to(PATH_SIGN_IN), navigationContent);
        } catch (UsernameTakenException e) {
            return errorAlreadyExists(navigationContent);
        }

    }

    private UnifiedModelAndView errorAlreadyExists(NavigationContent navigationContent) {
        final UnifiedModelAndView modelAndView = new UnifiedModelAndView(SEGMENT_SIGN_UP, navigationContent);
        modelAndView.addObject("error_user_exists", true);
        return modelAndView;
    }
}

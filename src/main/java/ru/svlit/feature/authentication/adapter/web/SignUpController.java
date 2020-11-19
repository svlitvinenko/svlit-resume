package ru.svlit.feature.authentication.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.Redirect;
import ru.svlit.core.util.UnifiedModelAndView;
import ru.svlit.feature.authentication.application.port.in.InitializeSignUpUseCase;
import ru.svlit.feature.authentication.application.port.in.InitializeSignUpUseCase.InitializeSignUpCommand;
import ru.svlit.feature.authentication.application.port.in.InitializeSignUpUseCase.UsernameTakenException;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase.GetNavigationContentCommand;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.*;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping(PATH_SIGN_UP)
class SignUpController {

    private final InitializeSignUpUseCase initializeSignUpUseCase;
    private final GetNavigationContentUseCase getNavigationContentUseCase;

    @GetMapping
    public UnifiedModelAndView getSignUpForm() {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(false)
        );
        return new UnifiedModelAndView(SEGMENT_SIGN_UP, navigationContent);
    }

    @PostMapping
    public String signUpWithCredentials(final String username, final String password, final String email, RedirectAttributes redirectAttributes, Model model) {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(false)
        );
        final InitializeSignUpCommand command = new InitializeSignUpCommand(username, password, email);

        try {
            initializeSignUpUseCase.signUp(command);
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("email", email);
            return Redirect.to(PATH_ACTIVATION);
        } catch (UsernameTakenException e) {
            return errorAlreadyExists(navigationContent, model);
        }

    }

    private String errorAlreadyExists(NavigationContent navigationContent, Model model) {
        final UnifiedModelAndView modelAndView = new UnifiedModelAndView(SEGMENT_SIGN_UP, navigationContent);
        modelAndView.addObject("error_user_exists", true);
        model.addAllAttributes(modelAndView.getModel());
        return PATH_SIGN_UP;
    }
}

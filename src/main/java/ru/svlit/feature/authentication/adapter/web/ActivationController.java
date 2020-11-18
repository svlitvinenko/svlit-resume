package ru.svlit.feature.authentication.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.util.Redirect;
import ru.svlit.feature.authentication.application.port.in.ConfirmSignUpUseCase;
import ru.svlit.feature.authentication.application.port.in.ConfirmSignUpUseCase.ActivationCodeNotFoundException;
import ru.svlit.feature.authentication.application.port.in.ConfirmSignUpUseCase.ConfirmSignUpCommand;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_ACTIVATION;
import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_SIGN_IN;

/**
 * Контроллер активации аккаунтов после регистрации.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@WebAdapter
@RequiredArgsConstructor
@RequestMapping(PATH_ACTIVATION)
class ActivationController {

    private static final String PATH_ACTIVATE_BY_CODE = "/{code}";

    private final ConfirmSignUpUseCase confirmSignUpUseCase;

    @GetMapping(PATH_ACTIVATE_BY_CODE)
    public String activate(@PathVariable String code) {
        try {
            confirmSignUpUseCase.confirm(new ConfirmSignUpCommand(code));
        } catch (ActivationCodeNotFoundException e) {
            // ignored
        }
        return Redirect.to(PATH_SIGN_IN);
    }
}

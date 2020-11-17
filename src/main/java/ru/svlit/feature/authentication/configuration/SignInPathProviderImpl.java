package ru.svlit.feature.authentication.configuration;

import org.springframework.stereotype.Component;
import ru.svlit.core.global.configuration.security.paths.SignInPathProvider;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_SIGN_IN;

@Component
public class SignInPathProviderImpl implements SignInPathProvider {
    @Override
    public String getSignInPath() {
        return PATH_SIGN_IN;
    }
}

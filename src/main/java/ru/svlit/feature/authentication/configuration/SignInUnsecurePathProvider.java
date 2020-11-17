package ru.svlit.feature.authentication.configuration;

import org.springframework.stereotype.Component;
import ru.svlit.core.global.configuration.security.paths.UnsecuredPathProvider;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_SIGN_IN;

@Component
public class SignInUnsecurePathProvider implements UnsecuredPathProvider {
    @Override
    public String getPathPattern() {
        return PATH_SIGN_IN;
    }
}

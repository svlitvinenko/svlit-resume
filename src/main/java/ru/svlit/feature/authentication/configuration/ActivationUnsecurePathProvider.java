package ru.svlit.feature.authentication.configuration;

import org.springframework.stereotype.Component;
import ru.svlit.core.global.configuration.security.paths.UnsecuredPathProvider;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_ACTIVATION;

/**
 * Поставщик пути активации для обеспечения доступа к функционалу без авторизации.
 */
@Component
public class ActivationUnsecurePathProvider implements UnsecuredPathProvider {
    @Override
    public String getPathPattern() {
        return PATH_ACTIVATION + "/**";
    }
}

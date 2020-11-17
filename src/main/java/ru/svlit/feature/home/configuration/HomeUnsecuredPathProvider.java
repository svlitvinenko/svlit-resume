package ru.svlit.feature.home.configuration;

import org.springframework.stereotype.Component;
import ru.svlit.core.global.configuration.security.paths.UnsecuredPathProvider;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_HOME;

@Component
public class HomeUnsecuredPathProvider implements UnsecuredPathProvider {
    @Override
    public String getPathPattern() {
        return PATH_HOME;
    }
}

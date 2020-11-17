package ru.svlit.feature.coronadash.configuration;

import org.springframework.stereotype.Component;
import ru.svlit.core.global.configuration.security.paths.UnsecuredPathProvider;

import static ru.svlit.feature.coronadash.configuration.CoronaDashConfigurationConstants.PATH_SEGMENT_COVID;

@Component
public class CovidUnsecuredPathProvider implements UnsecuredPathProvider {
    @Override
    public String getPathPattern() {
        return PATH_SEGMENT_COVID;
    }
}

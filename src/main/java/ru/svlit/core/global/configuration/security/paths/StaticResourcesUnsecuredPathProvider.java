package ru.svlit.core.global.configuration.security.paths;

import org.springframework.stereotype.Component;

@Component
public class StaticResourcesUnsecuredPathProvider implements UnsecuredPathProvider {

    private static final String PATH_PATTERN_STATIC_RESOURCES = "/static/**";

    @Override
    public String getPathPattern() {
        return PATH_PATTERN_STATIC_RESOURCES;
    }
}

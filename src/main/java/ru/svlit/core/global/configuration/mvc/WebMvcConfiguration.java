package ru.svlit.core.global.configuration.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final String SLASH = "/";
    private static final String CLASSPATH_PREFIX = "classpath:";
    private static final String ALL_POSTFIX = "**";
    private static final String PATH_PATTERN_STATIC_RESOURCES = SLASH + "static";
    private static final String PATH_PATTERN_IMAGES = SLASH + "img";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_PATTERN_STATIC_RESOURCES + SLASH + ALL_POSTFIX)
                .addResourceLocations(CLASSPATH_PREFIX + PATH_PATTERN_STATIC_RESOURCES + SLASH);

        registry.addResourceHandler(PATH_PATTERN_IMAGES + ALL_POSTFIX)
                .addResourceLocations(CLASSPATH_PREFIX + PATH_PATTERN_STATIC_RESOURCES + PATH_PATTERN_IMAGES + SLASH);
    }
}

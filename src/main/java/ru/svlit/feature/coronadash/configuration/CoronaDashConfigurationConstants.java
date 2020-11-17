package ru.svlit.feature.coronadash.configuration;

public final class CoronaDashConfigurationConstants {
    private static final String SLASH = "/";
    public static final String CONFIGURATION_PREFIX = "corona-dash";
    public static final String PROPERTY_REFRESH_DELAY_MS = "refresh-delay-ms";
    public static final String SPEL_REFRESH_DELAY_MS = "${" + CONFIGURATION_PREFIX + "." + PROPERTY_REFRESH_DELAY_MS + "}";

    public static final String SEGMENT_COVID = "covid";

    public static final String PATH_SEGMENT_COVID = SLASH + SEGMENT_COVID;
}

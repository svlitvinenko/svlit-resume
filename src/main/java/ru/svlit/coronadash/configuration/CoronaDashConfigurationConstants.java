package ru.svlit.coronadash.configuration;

public final class CoronaDashConfigurationConstants {
    public static final String CONFIGURATION_PREFIX = "corona-dash";
    public static final String PROPERTY_REFRESH_DELAY_MS = "refresh-delay-ms";
    public static final String SPEL_REFRESH_DELAY_MS = "${" + CONFIGURATION_PREFIX + "." + PROPERTY_REFRESH_DELAY_MS + "}";
}

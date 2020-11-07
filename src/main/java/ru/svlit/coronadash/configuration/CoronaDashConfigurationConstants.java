package ru.svlit.coronadash.configuration;

public final class CoronaDashConfigurationConstants {
    public static final String CONFIGURATION_PREFIX = "corona-dash";
    public static final String PROPERTY_REFRESH_DELAY_MS = "refresh-delay-ms";
    public static final String SPEL_REFRESH_DELAY_MS = "${" + CONFIGURATION_PREFIX + "." + PROPERTY_REFRESH_DELAY_MS + "}";
    public static final String ENV_CD_DATABASE_PROTOCOL = "CD_DATABASE_PROTOCOL";
    public static final String ENV_CD_DATABASE_HOST = "CD_DATABASE_HOST";
    public static final String ENV_CD_DATABASE_USER = "CD_DATABASE_USER";
    public static final String ENV_CD_DATABASE_PASSWORD = "CD_DATABASE_PASSWORD";
    public static final String ENV_CD_DATABASE_NAME = "CD_DATABASE_NAME";
}

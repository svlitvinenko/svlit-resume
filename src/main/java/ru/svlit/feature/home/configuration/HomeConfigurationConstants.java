package ru.svlit.feature.home.configuration;

public final class HomeConfigurationConstants {
    public static final String CONFIGURATION_PREFIX = "home";

    private static final String SLASH = "/";

    public static final String SEGMENT_SIGN_IN = "sign-in";
    public static final String SEGMENT_SIGN_UP = "sign-up";
    public static final String SEGMENT_ACTIVATION = "activation";

    public static final String PATH_SIGN_IN = SLASH + SEGMENT_SIGN_IN;
    public static final String PATH_SIGN_UP = SLASH + SEGMENT_SIGN_UP;
    public static final String PATH_ACTIVATION = SLASH + SEGMENT_ACTIVATION;
    public static final String PATH_HOME = SLASH;
}

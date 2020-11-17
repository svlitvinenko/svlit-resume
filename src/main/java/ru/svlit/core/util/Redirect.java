package ru.svlit.core.util;

public final class Redirect {
    private static final String REDIRECT_TO = "redirect:";

    public static String to(String path) {
        return REDIRECT_TO + path;
    }
}

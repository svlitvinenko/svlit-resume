package ru.svlit.core.globalization.domain;

/**
 * Поддерживаемые локали системы.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
public enum SupportedLocale {
    RU, EN;

    public static SupportedLocale getDefaultLocale() {
        return EN;
    }
}

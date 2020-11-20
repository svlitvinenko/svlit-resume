package ru.svlit.core.globalization;

import java.util.Locale;

/**
 * Менеджер ресурсов.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
public interface ResourceManager {
    String getText(Resource resource, Locale locale, Object[] args);
    String getText(Resource resource, Object[] args);
    String getText(Resource resource);
}

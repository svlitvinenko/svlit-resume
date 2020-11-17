package ru.svlit.core.global.configuration.security.paths;

public interface UnsecuredPathProvider {

    /**
     * Возвращает паттерн, для которого устанавливается правило.
     */
    String getPathPattern();
}

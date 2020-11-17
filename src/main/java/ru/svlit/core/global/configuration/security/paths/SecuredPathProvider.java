package ru.svlit.core.global.configuration.security.paths;

import ru.svlit.core.global.configuration.security.Role;

import java.util.Set;

public interface SecuredPathProvider {

    /**
     * Возвращает паттерн, для которого устанавливается правило.
     */
    String getPathPattern();

    /**
     * Возвращает роли пользователя, с которыми он может получить доступ к пути
     * из {@link SecuredPathProvider#getPathPattern()}. Достаточно иметь хотя бы одну из возвращённых ролей.
     */
    Set<Role> getRoles();
}

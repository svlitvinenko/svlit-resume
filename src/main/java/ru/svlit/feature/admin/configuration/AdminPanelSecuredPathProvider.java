package ru.svlit.feature.admin.configuration;

import org.springframework.stereotype.Component;
import ru.svlit.core.global.configuration.security.Role;
import ru.svlit.core.global.configuration.security.paths.SecuredPathProvider;

import java.util.Set;

import static java.util.Collections.singleton;
import static ru.svlit.core.global.configuration.security.Role.ADMIN;

/**
 * Правило доступа к панели администрирования только для клиентов с ролью "Администратор".
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Component
public class AdminPanelSecuredPathProvider implements SecuredPathProvider {

    private static final String PATH_PATTERN = "/admin/**";

    @Override
    public String getPathPattern() {
        return PATH_PATTERN;
    }

    @Override
    public Set<Role> getRoles() {
        return singleton(ADMIN);
    }
}

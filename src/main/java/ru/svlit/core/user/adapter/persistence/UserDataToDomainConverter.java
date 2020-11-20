package ru.svlit.core.user.adapter.persistence;

import org.springframework.stereotype.Service;
import ru.svlit.core.global.configuration.security.Role;
import ru.svlit.core.globalization.domain.SupportedLocale;
import ru.svlit.feature.authentication.domain.User;

import static java.util.stream.Collectors.toSet;
import static ru.svlit.core.globalization.domain.SupportedLocale.getDefaultLocale;
import static ru.svlit.core.globalization.domain.SupportedLocale.valueOf;

@Service
public class UserDataToDomainConverter {

    public User convert(UserModel userModel) {
        return new User(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getRoles().stream().map(this::toRole).collect(toSet()),
                userModel.isActive(),
                getLocaleOrDefault(userModel.getLocale())
        );
    }

    private Role toRole(String roleString) {
        return Role.valueOf(roleString);
    }

    private SupportedLocale getLocaleOrDefault(String locale) {
        if (locale == null) {
            return getDefaultLocale();
        }
        try {
            return valueOf(locale);
        } catch (IllegalArgumentException e) {
            return getDefaultLocale();
        }
    }
}

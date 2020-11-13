package ru.svlit.feature.authentication.adapter.persistence;

import org.springframework.stereotype.Service;
import ru.svlit.feature.authentication.domain.Role;
import ru.svlit.feature.authentication.domain.User;

import static java.util.stream.Collectors.toSet;

@Service
public class UserDomainToDataConverter {

    public UserModel convert(User user) {
        return new UserModel(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(Role::name).collect(toSet()),
                user.isActive()
        );
    }
}

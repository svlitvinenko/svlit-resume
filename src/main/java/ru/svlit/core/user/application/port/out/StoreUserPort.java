package ru.svlit.core.user.application.port.out;

import ru.svlit.feature.authentication.domain.User;

public interface StoreUserPort {
    void store(User user);
}

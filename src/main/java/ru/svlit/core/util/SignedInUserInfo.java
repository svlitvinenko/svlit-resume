package ru.svlit.core.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.feature.authentication.domain.User;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class SignedInUserInfo implements UserInfo {
    private final User user;
}

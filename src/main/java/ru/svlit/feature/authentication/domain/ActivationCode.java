package ru.svlit.feature.authentication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Код активации. Нужен для подтверждения почты клиента и активации его аккаунта.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ActivationCode {
    private final String activationCode;
    private final String userId;
}

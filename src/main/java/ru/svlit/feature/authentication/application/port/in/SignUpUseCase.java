package ru.svlit.feature.authentication.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public interface SignUpUseCase {

    void signUp(SignUpCommand command) throws UsernameTakenException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class SignUpCommand {
        private final String username;
        private final String password;
    }

    class UsernameTakenException extends Exception {

    }
}

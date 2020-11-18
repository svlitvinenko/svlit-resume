package ru.svlit.feature.authentication.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public interface InitializeSignUpUseCase {

    void signUp(InitializeSignUpCommand command) throws UsernameTakenException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class InitializeSignUpCommand {
        private final String username;
        private final String password;
        private final String email;
    }

    class UsernameTakenException extends Exception {

    }
}

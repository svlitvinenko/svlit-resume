package ru.svlit.feature.wall.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public interface SubmitMessageUseCase {
    void submit(SubmitMessageCommand command) throws EmptyMessageTextException, UserNotFoundException;

    class EmptyMessageTextException extends Exception {
    }

    class UserNotFoundException extends Exception {
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class SubmitMessageCommand {
        private final String text;
        private final String tag;
    }
}

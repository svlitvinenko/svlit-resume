package ru.svlit.feature.wall.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.feature.wall.application.model.Message;

public interface FindMessagesByTagUseCase {
    Iterable<Message> findByTag(FindMessagesByTagCommand command);

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class FindMessagesByTagCommand {
        private final String tag;
    }
}

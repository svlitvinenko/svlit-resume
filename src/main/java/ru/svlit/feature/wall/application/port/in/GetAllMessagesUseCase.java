package ru.svlit.feature.wall.application.port.in;

import ru.svlit.feature.wall.application.model.Message;

public interface GetAllMessagesUseCase {
    Iterable<Message> perform();
}

package ru.svlit.feature.wall.application.port.out;

import ru.svlit.feature.wall.application.model.Message;

public interface GetAllMessagesPort {
    Iterable<Message> getAllMessages();
}

package ru.svlit.feature.sweater.application.port.out;

import ru.svlit.feature.sweater.application.model.Message;

public interface GetAllMessagesPort {
    Iterable<Message> getAllMessages();
}

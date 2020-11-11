package ru.svlit.feature.sweater.application.port.in;

import ru.svlit.feature.sweater.application.model.Message;

public interface GetAllMessagesUseCase {
    Iterable<Message> perform();
}

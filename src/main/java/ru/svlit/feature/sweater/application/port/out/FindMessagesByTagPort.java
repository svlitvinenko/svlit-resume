package ru.svlit.feature.sweater.application.port.out;

import ru.svlit.feature.sweater.application.model.Message;

public interface FindMessagesByTagPort {
    Iterable<Message> findMessagesByTag(String tag);
}

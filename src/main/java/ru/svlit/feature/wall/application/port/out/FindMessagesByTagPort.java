package ru.svlit.feature.wall.application.port.out;

import ru.svlit.feature.wall.application.model.Message;

public interface FindMessagesByTagPort {
    Iterable<Message> findMessagesByTag(String tag);
}

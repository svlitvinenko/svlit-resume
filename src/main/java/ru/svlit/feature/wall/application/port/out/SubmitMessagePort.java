package ru.svlit.feature.wall.application.port.out;

import ru.svlit.feature.wall.application.model.Message;

public interface SubmitMessagePort {

    void submit(Message message);
}

package ru.svlit.feature.sweater.application.port.out;

import ru.svlit.feature.sweater.application.model.Message;

public interface SubmitMessagePort {

    void submit(Message message);
}

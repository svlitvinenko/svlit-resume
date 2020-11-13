package ru.svlit.feature.sweater.adapter.persistence.converter;

import org.springframework.stereotype.Service;
import ru.svlit.feature.sweater.adapter.persistence.datasource.model.MessageModel;
import ru.svlit.feature.sweater.application.model.Message;

@Service
public class MessageDataToDomainConverter {
    public Message convert(MessageModel model) {
        return new Message(
                model.getId(),
                model.getText(),
                model.getTag(),
                model.getAuthor()
        );
    }
}

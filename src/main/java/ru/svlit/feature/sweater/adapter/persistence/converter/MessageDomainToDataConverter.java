package ru.svlit.feature.sweater.adapter.persistence.converter;

import org.springframework.stereotype.Service;
import ru.svlit.feature.sweater.adapter.persistence.datasource.model.MessageModel;
import ru.svlit.feature.sweater.application.model.Message;

@Service
public class MessageDomainToDataConverter {
    public MessageModel convert(Message message) {
        return new MessageModel(
                message.getId(),
                message.getText(),
                message.getTag(),
                message.getAuthor()
        );
    }
}

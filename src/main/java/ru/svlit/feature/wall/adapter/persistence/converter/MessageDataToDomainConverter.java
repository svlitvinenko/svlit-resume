package ru.svlit.feature.wall.adapter.persistence.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.wall.adapter.persistence.datasource.model.MessageModel;
import ru.svlit.feature.wall.application.model.Message;

@Service
@RequiredArgsConstructor
public class MessageDataToDomainConverter {

    public Message convert(MessageModel model, User user) {
        return new Message(
                model.getId(),
                model.getText(),
                model.getTag(),
                user
        );
    }
}

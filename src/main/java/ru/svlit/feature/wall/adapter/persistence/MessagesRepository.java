package ru.svlit.feature.wall.adapter.persistence;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.core.user.application.port.in.FindUserByIdUseCase;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.wall.adapter.persistence.converter.MessageDataToDomainConverter;
import ru.svlit.feature.wall.adapter.persistence.converter.MessageDomainToDataConverter;
import ru.svlit.feature.wall.adapter.persistence.datasource.MessageDataSource;
import ru.svlit.feature.wall.adapter.persistence.datasource.model.MessageModel;
import ru.svlit.feature.wall.application.model.Message;
import ru.svlit.feature.wall.application.port.out.FindMessagesByTagPort;
import ru.svlit.feature.wall.application.port.out.GetAllMessagesPort;
import ru.svlit.feature.wall.application.port.out.SubmitMessagePort;

import java.util.ArrayList;
import java.util.List;

import static ru.svlit.feature.authentication.domain.User.unknown;

@PersistenceAdapter
@RequiredArgsConstructor
class MessagesRepository implements GetAllMessagesPort, FindMessagesByTagPort, SubmitMessagePort {

    private final FindUserByIdUseCase findUserByIdUseCase;
    private final MessageDataSource messageDataSource;
    private final MessageDataToDomainConverter messageDataToDomainConverter;
    private final MessageDomainToDataConverter messageDomainToDataConverter;

    @Override
    public Iterable<Message> getAllMessages() {
        final Iterable<MessageModel> modelsIterable = messageDataSource.findAll();
        final List<Message> modelsList = new ArrayList<>();
        modelsIterable.forEach(model -> modelsList.add(convertFromData(model)));
        return modelsList;
    }

    @Override
    public Iterable<Message> findMessagesByTag(String tag) {
        final Iterable<MessageModel> modelsIterable = messageDataSource.findByTag(tag);
        final List<Message> modelsList = new ArrayList<>();
        modelsIterable.forEach(model -> modelsList.add(convertFromData(model)));
        return modelsList;
    }

    @Override
    public void submit(Message message) {
        final MessageModel messageModel = messageDomainToDataConverter.convert(message);
        messageDataSource.save(messageModel);
    }

    private Message convertFromData(MessageModel messageModel) {
        final User user = findUserByIdUseCase.findByUd(messageModel.getAuthorId()).orElse(unknown());
        return messageDataToDomainConverter.convert(messageModel, user);

    }
}

package ru.svlit.feature.sweater.adapter.persistence;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.feature.sweater.adapter.persistence.converter.MessageDataToDomainConverter;
import ru.svlit.feature.sweater.adapter.persistence.converter.MessageDomainToDataConverter;
import ru.svlit.feature.sweater.adapter.persistence.datasource.MessageDataSource;
import ru.svlit.feature.sweater.adapter.persistence.datasource.model.MessageModel;
import ru.svlit.feature.sweater.application.model.Message;
import ru.svlit.feature.sweater.application.port.out.FindMessagesByTagPort;
import ru.svlit.feature.sweater.application.port.out.GetAllMessagesPort;
import ru.svlit.feature.sweater.application.port.out.SubmitMessagePort;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class MessagesRepository implements GetAllMessagesPort, FindMessagesByTagPort, SubmitMessagePort {

    private final MessageDataSource messageDataSource;
    private final MessageDataToDomainConverter messageDataToDomainConverter;
    private final MessageDomainToDataConverter messageDomainToDataConverter;

    @Override
    public Iterable<Message> getAllMessages() {
        final Iterable<MessageModel> modelsIterable = messageDataSource.findAll();
        final List<Message> modelsList = new ArrayList<>();
        modelsIterable.forEach(model -> modelsList.add(messageDataToDomainConverter.convert(model)));
        return modelsList;
    }

    @Override
    public Iterable<Message> findMessagesByTag(String tag) {
        final Iterable<MessageModel> modelsIterable = messageDataSource.findByTag(tag);
        final List<Message> modelsList = new ArrayList<>();
        modelsIterable.forEach(model -> modelsList.add(messageDataToDomainConverter.convert(model)));
        return modelsList;
    }

    @Override
    public void submit(Message message) {
        final MessageModel messageModel = messageDomainToDataConverter.convert(message);
        messageDataSource.save(messageModel);
    }
}

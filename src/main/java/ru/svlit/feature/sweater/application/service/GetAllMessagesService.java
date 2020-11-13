package ru.svlit.feature.sweater.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.sweater.application.model.Message;
import ru.svlit.feature.sweater.application.port.in.GetAllMessagesUseCase;
import ru.svlit.feature.sweater.application.port.out.GetAllMessagesPort;

@UseCase
@RequiredArgsConstructor
class GetAllMessagesService implements GetAllMessagesUseCase {

    private final GetAllMessagesPort getAllMessagesPort;

    @Override
    public Iterable<Message> perform() {
        return getAllMessagesPort.getAllMessages();
    }
}

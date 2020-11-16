package ru.svlit.feature.wall.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.wall.application.model.Message;
import ru.svlit.feature.wall.application.port.in.GetAllMessagesUseCase;
import ru.svlit.feature.wall.application.port.out.GetAllMessagesPort;

@UseCase
@RequiredArgsConstructor
class GetAllMessagesService implements GetAllMessagesUseCase {

    private final GetAllMessagesPort getAllMessagesPort;

    @Override
    public Iterable<Message> perform() {
        return getAllMessagesPort.getAllMessages();
    }
}

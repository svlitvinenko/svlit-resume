package ru.svlit.feature.wall.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.wall.application.model.Message;
import ru.svlit.feature.wall.application.port.in.FindMessagesByTagUseCase;
import ru.svlit.feature.wall.application.port.out.FindMessagesByTagPort;
import ru.svlit.feature.wall.application.port.out.GetAllMessagesPort;

@UseCase
@RequiredArgsConstructor
class FindMessagesByTagService implements FindMessagesByTagUseCase {

    private final FindMessagesByTagPort findMessagesByTagPort;
    private final GetAllMessagesPort getAllMessagesPort;

    @Override
    public Iterable<Message> findByTag(final FindMessagesByTagCommand command) {
        if (command.getTag() == null || command.getTag().isBlank()) {
            return getAllMessagesPort.getAllMessages();
        } else {
            return findMessagesByTagPort.findMessagesByTag(command.getTag());
        }
    }
}

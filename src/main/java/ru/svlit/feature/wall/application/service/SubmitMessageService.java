package ru.svlit.feature.wall.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.user.application.port.in.GetCurrentUserUseCase;
import ru.svlit.feature.authentication.domain.User;
import ru.svlit.feature.wall.application.model.Message;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase;
import ru.svlit.feature.wall.application.port.out.SubmitMessagePort;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
class SubmitMessageService implements SubmitMessageUseCase {

    private final SubmitMessagePort submitMessagePort;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Override
    public void submit(final SubmitMessageCommand command) throws EmptyMessageTextException, UserNotFoundException {
        final String text = getTextOrThrowEmptyMessageException(command.getText());
        final String tag = getTagOrDefault(command.getTag());
        final String id = UUID.randomUUID().toString();
        final User currentUser = getCurrentUserUseCase.getCurrentUser().orElseThrow(UserNotFoundException::new);

        submitMessagePort.submit(new Message(id, text, tag, currentUser));
    }

    private String getTagOrDefault(String tag) {
        if (tag != null && !tag.isBlank()) {
            return tag.trim();
        } else {
            return "";
        }
    }

    private String getTextOrThrowEmptyMessageException(String text) throws EmptyMessageTextException {
        if (text == null || text.isBlank()) {
            throw new EmptyMessageTextException();
        } else {
            return text.trim();
        }
    }
}

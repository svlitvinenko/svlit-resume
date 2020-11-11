package ru.svlit.feature.sweater.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.sweater.application.model.Message;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase;
import ru.svlit.feature.sweater.application.port.out.SubmitMessagePort;

@UseCase
@RequiredArgsConstructor
class SubmitMessageService implements SubmitMessageUseCase {

    private final SubmitMessagePort submitMessagePort;

    @Override
    public void submit(final SubmitMessageCommand command) throws EmptyMessageTextException {
        final String text = getTextOrThrowEmptyMessageException(command.getText());
        final String tag = getTagOrDefault(command.getTag());

        submitMessagePort.submit(new Message(text, tag));
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

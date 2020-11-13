package ru.svlit.feature.sweater.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.UnifiedModelAndView;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase.GetNavigationContentCommand;
import ru.svlit.feature.sweater.application.model.Message;
import ru.svlit.feature.sweater.application.port.in.FindMessagesByTagUseCase;
import ru.svlit.feature.sweater.application.port.in.FindMessagesByTagUseCase.FindMessagesByTagCommand;
import ru.svlit.feature.sweater.application.port.in.GetAllMessagesUseCase;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase.EmptyMessageTextException;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase.SubmitMessageCommand;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase.UserNotFoundException;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/sweater")
class SweaterController {
    private final GetNavigationContentUseCase getNavigationContentUseCase;
    private final GetAllMessagesUseCase getAllMessagesUseCase;
    private final FindMessagesByTagUseCase findMessagesByTagUseCase;
    private final SubmitMessageUseCase submitMessageUseCase;

    @GetMapping
    public UnifiedModelAndView getMain(@RequestParam(required = false) final String filter) {
        if (filter != null && !filter.isBlank()) {
            final Iterable<Message> filteredByTag = findMessagesByTagUseCase.findByTag(new FindMessagesByTagCommand(filter));
            return getViewForMessages(filteredByTag);
        } else {
            return getViewForAllMessages();
        }
    }

    @PostMapping
    public UnifiedModelAndView add(@RequestParam String text, @RequestParam String tag) throws EmptyMessageTextException, UserNotFoundException {
        submitMessageUseCase.submit(new SubmitMessageCommand(text, tag));
        return getViewForAllMessages();
    }

    private UnifiedModelAndView getViewForMessages(Iterable<Message> messages) {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(true)
        );
        final UnifiedModelAndView modelAndView = new UnifiedModelAndView("sweater", navigationContent);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    private UnifiedModelAndView getViewForAllMessages() {
        final Iterable<Message> messages = getAllMessagesUseCase.perform();
        return getViewForMessages(messages);
    }
}

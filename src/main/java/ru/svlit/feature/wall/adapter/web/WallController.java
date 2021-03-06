package ru.svlit.feature.wall.adapter.web;

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
import ru.svlit.feature.wall.application.model.Message;
import ru.svlit.feature.wall.application.port.in.FindMessagesByTagUseCase;
import ru.svlit.feature.wall.application.port.in.FindMessagesByTagUseCase.FindMessagesByTagCommand;
import ru.svlit.feature.wall.application.port.in.GetAllMessagesUseCase;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase.EmptyMessageTextException;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase.SubmitMessageCommand;
import ru.svlit.feature.wall.application.port.in.SubmitMessageUseCase.UserNotFoundException;

import static ru.svlit.feature.wall.configuration.WallConfigurationConstants.PATH_WALL;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping(PATH_WALL)
class WallController {
    private final GetNavigationContentUseCase getNavigationContentUseCase;
    private final GetAllMessagesUseCase getAllMessagesUseCase;
    private final FindMessagesByTagUseCase findMessagesByTagUseCase;
    private final SubmitMessageUseCase submitMessageUseCase;

    @GetMapping
    public UnifiedModelAndView getMain(@RequestParam(required = false) final String filter) {
        if (filter != null && !filter.isBlank()) {
            final Iterable<Message> filteredByTag = findMessagesByTagUseCase.findByTag(new FindMessagesByTagCommand(filter));
            return getViewForMessages(filteredByTag, filter);
        } else {
            return getViewForAllMessages();
        }
    }

    @PostMapping
    public UnifiedModelAndView add(@RequestParam String text, @RequestParam String tag) throws EmptyMessageTextException, UserNotFoundException {
        submitMessageUseCase.submit(new SubmitMessageCommand(text, tag));
        return getViewForAllMessages();
    }

    private UnifiedModelAndView getViewForMessages(Iterable<Message> messages, String filter) {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentCommand(true)
        );
        final UnifiedModelAndView modelAndView = new UnifiedModelAndView("wall", navigationContent);
        modelAndView.addObject("messages", messages);
        modelAndView.addObject("filter", filter);
        modelAndView.addObject("maxLength", 280);
        return modelAndView;
    }

    private UnifiedModelAndView getViewForAllMessages() {
        final Iterable<Message> messages = getAllMessagesUseCase.perform();
        return getViewForMessages(messages, "");
    }
}

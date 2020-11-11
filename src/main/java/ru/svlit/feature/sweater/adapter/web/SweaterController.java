package ru.svlit.feature.sweater.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.core.adapter.web.UnifiedModelAndView;
import ru.svlit.core.adapter.web.UnifiedModelAndView.NavigationContent;
import ru.svlit.entry.home.application.port.in.GetNavigationContentUseCase;
import ru.svlit.feature.sweater.application.model.Message;
import ru.svlit.feature.sweater.application.port.in.FindMessagesByTagUseCase;
import ru.svlit.feature.sweater.application.port.in.FindMessagesByTagUseCase.FindMessagesByTagCommand;
import ru.svlit.feature.sweater.application.port.in.GetAllMessagesUseCase;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase.EmptyMessageTextException;
import ru.svlit.feature.sweater.application.port.in.SubmitMessageUseCase.SubmitMessageCommand;

@WebAdapter
@RequestMapping("/sweater")
@RequiredArgsConstructor
class SweaterController {
    private final GetNavigationContentUseCase getNavigationContentUseCase;
    private final GetAllMessagesUseCase getAllMessagesUseCase;
    private final FindMessagesByTagUseCase findMessagesByTagUseCase;
    private final SubmitMessageUseCase submitMessageUseCase;

    @GetMapping
    public UnifiedModelAndView getMain() {
        final NavigationContent navigationContent = getNavigationContentUseCase.perform();
        final Iterable<Message> perform = getAllMessagesUseCase.perform();
        final UnifiedModelAndView modelAndView = new UnifiedModelAndView("sweater", navigationContent);
        modelAndView.addObject("messages", perform);
        return modelAndView;
    }

    @PostMapping
    public UnifiedModelAndView add(@RequestParam String text, @RequestParam String tag) throws EmptyMessageTextException {
        submitMessageUseCase.submit(new SubmitMessageCommand(text, tag));
        return getMain();
    }

    @PostMapping("/filter")
    public UnifiedModelAndView filter(@RequestParam final String tag) {
        final Iterable<Message> messagesToShow;
        if (tag != null && !tag.isBlank()) {
            messagesToShow = findMessagesByTagUseCase.findByTag(new FindMessagesByTagCommand(tag));
        } else {
            messagesToShow = getAllMessagesUseCase.perform();
        }

        final NavigationContent navigationContent = getNavigationContentUseCase.perform();
        final UnifiedModelAndView modelAndView = new UnifiedModelAndView("sweater", navigationContent);
        modelAndView.addObject("messages", messagesToShow);
        return modelAndView;
    }
}

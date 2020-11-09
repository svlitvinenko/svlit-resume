package ru.svlit.feature.home.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.feature.home.application.entity.Contact;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;
import ru.svlit.feature.home.application.port.in.GetContactsUseCase;
import ru.svlit.feature.home.application.port.in.GetEntryPointsUseCase;
import ru.svlit.feature.home.application.port.in.GetMessageOfTheDayUseCase;
import ru.svlit.feature.home.adapter.web.converter.HomePageFiller;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
class HomeController {
    private final GetMessageOfTheDayUseCase getMessageOfTheDayUseCase;
    private final GetEntryPointsUseCase getEntryPointsUseCase;
    private final GetContactsUseCase getContactsUseCase;
    private final HomePageFiller homePageFiller;

    @GetMapping
    public ModelAndView get() {
        final MessageOfTheDay messageOfTheDay = getMessageOfTheDayUseCase.perform();
        final List<EntryPointDescription> entryPoints = getEntryPointsUseCase.perform();
        final List<Contact> contacts = getContactsUseCase.perform();
        return homePageFiller.fill(messageOfTheDay, entryPoints, contacts);
    }
}

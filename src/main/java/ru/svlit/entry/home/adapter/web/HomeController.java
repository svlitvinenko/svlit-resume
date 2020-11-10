package ru.svlit.entry.home.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.svlit.core.adapter.web.UnifiedModelAndView;
import ru.svlit.core.adapter.web.UnifiedModelAndView.NavigationContent;
import ru.svlit.entry.home.adapter.web.converter.HomePageFiller;
import ru.svlit.entry.home.application.entity.Achievement;
import ru.svlit.entry.home.application.entity.MessageOfTheDay;
import ru.svlit.entry.home.application.port.in.GetAchievementsUseCase;
import ru.svlit.entry.home.application.port.in.GetMessageOfTheDayUseCase;
import ru.svlit.entry.home.application.port.in.GetNavigationContentUseCase;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
class HomeController {
    private final GetMessageOfTheDayUseCase getMessageOfTheDayUseCase;
    private final GetNavigationContentUseCase getNavigationContentUseCase;
    private final GetAchievementsUseCase getAchievementsUseCase;
    private final HomePageFiller homePageFiller;

    @GetMapping
    public ModelAndView get() throws GetMessageOfTheDayUseCase.CouldNotGetQuoteException {
        final MessageOfTheDay messageOfTheDay = getMessageOfTheDayUseCase.perform();
        final NavigationContent navigationContent = getNavigationContentUseCase.perform();
        final List<Achievement> achievements = getAchievementsUseCase.perform();
        return homePageFiller.fill(messageOfTheDay, navigationContent, achievements);
    }
}

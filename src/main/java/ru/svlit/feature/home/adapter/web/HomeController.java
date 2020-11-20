package ru.svlit.feature.home.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.svlit.core.globalization.ResourceManager;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.UnifiedModelFiller;
import ru.svlit.feature.home.application.entity.Achievement;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;
import ru.svlit.feature.home.application.port.in.GetAchievementsUseCase;
import ru.svlit.feature.home.application.port.in.GetMessageOfTheDayUseCase;
import ru.svlit.feature.home.application.port.in.GetMessageOfTheDayUseCase.CouldNotGetQuoteException;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;

import java.util.List;

import static ru.svlit.feature.home.configuration.HomeConfigurationConstants.PATH_HOME;

@Controller
@RequiredArgsConstructor
@RequestMapping(PATH_HOME)
class HomeController {
    private final GetMessageOfTheDayUseCase getMessageOfTheDayUseCase;
    private final GetNavigationContentUseCase getNavigationContentUseCase;
    private final GetAchievementsUseCase getAchievementsUseCase;
    private final ResourceManager resourceManager;

    @GetMapping
    public String get(Model model) throws CouldNotGetQuoteException {
        final MessageOfTheDay messageOfTheDay = getMessageOfTheDayUseCase.perform();
        final List<Achievement> achievements = getAchievementsUseCase.perform();

        final NavigationContent navigationContent = getNavigationContentUseCase.perform(
                new GetNavigationContentUseCase.GetNavigationContentCommand(true)
        );

        final UnifiedModelFiller filler = new HomePageFiller(
                navigationContent, resourceManager, messageOfTheDay, achievements
        );

        filler.fill(model);

        return "home";
    }
}

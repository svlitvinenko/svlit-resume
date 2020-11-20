package ru.svlit.feature.home.adapter.web;

import org.springframework.ui.Model;
import ru.svlit.core.globalization.ResourceManager;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.core.util.UnifiedModelFiller;
import ru.svlit.feature.home.adapter.web.entity.MotdModel;
import ru.svlit.feature.home.application.entity.Achievement;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;

import java.util.List;

public class HomePageFiller extends UnifiedModelFiller {

    private final MessageOfTheDay messageOfTheDay;
    private final List<Achievement> achievements;

    public HomePageFiller(NavigationContent navigationContent,
                          ResourceManager resourceManager,
                          MessageOfTheDay messageOfTheDay,
                          List<Achievement> achievements) {
        super(navigationContent, resourceManager);
        this.messageOfTheDay = messageOfTheDay;
        this.achievements = achievements;
    }

    @Override
    public void fill(Model model) {
        super.fill(model);
        model.addAttribute("motd", new MotdModel(messageOfTheDay.getMessage(), messageOfTheDay.getAuthor()));
        model.addAttribute("achievements", achievements);
    }
}

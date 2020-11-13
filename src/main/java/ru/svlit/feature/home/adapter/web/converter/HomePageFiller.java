package ru.svlit.feature.home.adapter.web.converter;

import org.springframework.stereotype.Service;
import ru.svlit.core.util.UnifiedModelAndView;
import ru.svlit.core.util.NavigationContent;
import ru.svlit.feature.home.adapter.web.entity.MotdModel;
import ru.svlit.feature.home.application.entity.Achievement;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;

import java.util.List;

@Service
public class HomePageFiller {

    public UnifiedModelAndView fill(MessageOfTheDay messageOfTheDay,
                                    NavigationContent navigationContent,
                                    List<Achievement> achievements) {
        UnifiedModelAndView model = new UnifiedModelAndView("home", navigationContent);
        model.addObject("motd", new MotdModel(messageOfTheDay.getMessage(), messageOfTheDay.getAuthor()));
        model.addObject("achievements", achievements);
        return model;
    }
}

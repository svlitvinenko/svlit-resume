package ru.svlit.entry.home.adapter.web.converter;

import org.springframework.stereotype.Service;
import ru.svlit.core.adapter.web.UnifiedModelAndView;
import ru.svlit.core.adapter.web.UnifiedModelAndView.NavigationContent;
import ru.svlit.entry.home.adapter.web.entity.MotdModel;
import ru.svlit.entry.home.application.entity.Achievement;
import ru.svlit.entry.home.application.entity.MessageOfTheDay;

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

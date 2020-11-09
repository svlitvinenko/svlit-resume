package ru.svlit.feature.home.adapter.web.converter;

import org.springframework.stereotype.Service;
import ru.svlit.core.adapter.web.UnifiedModelAndView;
import ru.svlit.core.adapter.web.UnifiedModelAndView.NavigationContent;
import ru.svlit.feature.home.adapter.web.entity.MotdModel;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;

@Service
public class HomePageFiller {

    public UnifiedModelAndView fill(MessageOfTheDay messageOfTheDay, NavigationContent navigationContent) {
        UnifiedModelAndView model = new UnifiedModelAndView("home", navigationContent);
        model.addObject("motd", new MotdModel(messageOfTheDay.getMessage(), messageOfTheDay.getAuthor()));
        return model;
    }
}

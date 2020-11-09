package ru.svlit.feature.home.adapter.web.converter;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.feature.home.application.entity.Contact;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;
import ru.svlit.feature.home.adapter.web.entity.MotdModel;

import java.util.List;

@Service
public class HomePageFiller {

    public ModelAndView fill(MessageOfTheDay messageOfTheDay, List<EntryPointDescription> entryPoints, List<Contact> contacts) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("motd", new MotdModel(messageOfTheDay.getMessage(), messageOfTheDay.getAuthor()));
        modelAndView.addObject("entryPoints", entryPoints);
        modelAndView.addObject("contacts", contacts);
        return modelAndView;
    }
}

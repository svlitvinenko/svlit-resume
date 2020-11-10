package ru.svlit.core.adapter.web;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.web.servlet.ModelAndView;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.entry.home.application.entity.Contact;

import java.util.List;

public class UnifiedModelAndView extends ModelAndView {

    public UnifiedModelAndView(String viewName, NavigationContent navigationContent) {
        super(viewName);
        setViewName(viewName);
        addObject("navigationContent", navigationContent);
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static final class NavigationContent {
        private final List<EntryPointDescription> entryPoints;
        private final List<Contact> contacts;
    }
}

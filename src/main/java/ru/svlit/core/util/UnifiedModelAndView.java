package ru.svlit.core.util;

import org.springframework.web.servlet.ModelAndView;

public class UnifiedModelAndView extends ModelAndView {

    private static final String OBJECT_NAVIGATION_CONTENT_KEY = "navigationContent";

    public UnifiedModelAndView(String viewName, NavigationContent navigationContent) {
        super(viewName);
        setViewName(viewName);
        addObject(OBJECT_NAVIGATION_CONTENT_KEY, navigationContent);
    }
}

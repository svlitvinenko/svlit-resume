package ru.svlit.core.util;

import org.springframework.web.servlet.ModelAndView;

public class UnifiedModelAndView extends ModelAndView {

    public UnifiedModelAndView(String viewName, NavigationContent navigationContent) {
        super(viewName);
        setViewName(viewName);
        addObject("navigationContent", navigationContent);
    }
}

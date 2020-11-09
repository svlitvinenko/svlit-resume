package ru.svlit.feature.home.application.port.in;

import ru.svlit.core.adapter.web.UnifiedModelAndView.NavigationContent;

public interface GetNavigationContentUseCase {
    NavigationContent perform();
}

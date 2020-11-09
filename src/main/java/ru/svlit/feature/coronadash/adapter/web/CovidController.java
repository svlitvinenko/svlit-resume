package ru.svlit.feature.coronadash.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.svlit.core.adapter.web.UnifiedModelAndView.NavigationContent;
import ru.svlit.feature.coronadash.adapter.web.converter.WorldWideStatisticsModelConverter;
import ru.svlit.feature.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.feature.coronadash.application.port.in.GetCoronavirusDataUseCase;
import ru.svlit.feature.coronadash.application.port.out.GetPersistedCoronavirusDataPort.NoPersistedCoronavirusDataException;
import ru.svlit.feature.coronadash.domain.World;
import ru.svlit.feature.home.application.port.in.GetNavigationContentUseCase;

@Controller
@RequestMapping("/covid")
@RequiredArgsConstructor
class CovidController {
    private final GetCoronavirusDataUseCase getCoronavirusDataUseCase;
    private final WorldWideStatisticsModelConverter worldWideStatisticsModelConverter;
    private final GetNavigationContentUseCase getNavigationContentUseCase;

    @GetMapping
    public ModelAndView covid() throws CouldNotFetchCoronavirusDataException, NoPersistedCoronavirusDataException {
        final World statistics = getCoronavirusDataUseCase.get();
        final NavigationContent navigationContent = getNavigationContentUseCase.perform();
        return worldWideStatisticsModelConverter.convert(statistics, navigationContent);
    }
}

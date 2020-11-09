package ru.svlit.feature.coronadash.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.svlit.feature.coronadash.adapter.web.converter.WorldWideStatisticsModelConverter;
import ru.svlit.feature.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.feature.coronadash.application.port.in.GetCoronavirusDataUseCase;
import ru.svlit.feature.coronadash.application.port.out.GetPersistedCoronavirusDataPort.NoPersistedCoronavirusDataException;
import ru.svlit.feature.coronadash.domain.World;

@Controller
@RequestMapping("/covid")
@RequiredArgsConstructor
class CovidController {
    private final GetCoronavirusDataUseCase getCoronavirusDataUseCase;
    private final WorldWideStatisticsModelConverter worldWideStatisticsModelConverter;

    @GetMapping
    public String covid(Model model) throws CouldNotFetchCoronavirusDataException, NoPersistedCoronavirusDataException {
        final World statistics = getCoronavirusDataUseCase.get();
        model.addAllAttributes(worldWideStatisticsModelConverter.convert(statistics));
        return "covid";
    }
}

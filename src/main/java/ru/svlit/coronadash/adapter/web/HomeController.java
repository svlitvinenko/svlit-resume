package ru.svlit.coronadash.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.svlit.coronadash.adapter.web.converter.WorldWideStatisticsModelConverter;
import ru.svlit.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.coronadash.application.port.in.GetCoronavirusDataUseCase;
import ru.svlit.coronadash.application.port.out.GetPersistedCoronavirusDataPort.NoPersistedCoronavirusDataException;
import ru.svlit.coronadash.domain.World;

@Controller("/")
@RequiredArgsConstructor
class HomeController {
    private final GetCoronavirusDataUseCase getCoronavirusDataUseCase;
    private final WorldWideStatisticsModelConverter worldWideStatisticsModelConverter;

    @GetMapping
    public String home(Model model) throws CouldNotFetchCoronavirusDataException, NoPersistedCoronavirusDataException {
        final World statistics = getCoronavirusDataUseCase.get();
        model.addAllAttributes(worldWideStatisticsModelConverter.convert(statistics));
        return "home";
    }
}

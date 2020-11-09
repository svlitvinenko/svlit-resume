package ru.svlit.feature.coronadash.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.svlit.feature.coronadash.application.port.in.GetCoronavirusDataUseCase;
import ru.svlit.feature.coronadash.application.port.out.GetPersistedCoronavirusDataPort;
import ru.svlit.feature.coronadash.application.port.out.GetPersistedCoronavirusDataPort.NoPersistedCoronavirusDataException;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.coronadash.configuration.CoronaDashConfiguration;
import ru.svlit.feature.coronadash.domain.World;

@Slf4j
@UseCase
@RequiredArgsConstructor
class GetCoronavirusDataService implements GetCoronavirusDataUseCase {

    private final GetPersistedCoronavirusDataPort getPersistedCoronavirusDataPort;
    private final CoronaDashConfiguration configuration;

    @Override
    public World get() throws NoPersistedCoronavirusDataException {
        log.info("Запрос на получение данных.");
        return getPersistedCoronavirusDataPort.get();
    }

}

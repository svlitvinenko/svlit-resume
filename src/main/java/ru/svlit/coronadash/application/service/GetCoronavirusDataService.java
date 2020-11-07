package ru.svlit.coronadash.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.svlit.coronadash.application.port.in.GetCoronavirusDataUseCase;
import ru.svlit.coronadash.application.port.out.GetPersistedCoronavirusDataPort;
import ru.svlit.coronadash.application.port.out.GetPersistedCoronavirusDataPort.NoPersistedCoronavirusDataException;
import ru.svlit.coronadash.architecture.UseCase;
import ru.svlit.coronadash.configuration.CoronaDashConfiguration;
import ru.svlit.coronadash.domain.World;

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

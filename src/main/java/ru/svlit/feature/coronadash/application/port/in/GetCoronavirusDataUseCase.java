package ru.svlit.feature.coronadash.application.port.in;

import ru.svlit.feature.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.feature.coronadash.application.port.out.GetPersistedCoronavirusDataPort;
import ru.svlit.feature.coronadash.domain.World;

public interface GetCoronavirusDataUseCase {
    World get() throws CouldNotFetchCoronavirusDataException, GetPersistedCoronavirusDataPort.NoPersistedCoronavirusDataException;
}

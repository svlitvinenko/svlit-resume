package ru.svlit.coronadash.application.port.in;

import ru.svlit.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.coronadash.application.port.out.GetPersistedCoronavirusDataPort;
import ru.svlit.coronadash.domain.World;

public interface GetCoronavirusDataUseCase {
    World get() throws CouldNotFetchCoronavirusDataException, GetPersistedCoronavirusDataPort.NoPersistedCoronavirusDataException;
}

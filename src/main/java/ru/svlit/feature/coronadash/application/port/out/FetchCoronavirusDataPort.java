package ru.svlit.feature.coronadash.application.port.out;

import ru.svlit.feature.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.feature.coronadash.domain.World;

public interface FetchCoronavirusDataPort {

    World get() throws CouldNotFetchCoronavirusDataException;
}

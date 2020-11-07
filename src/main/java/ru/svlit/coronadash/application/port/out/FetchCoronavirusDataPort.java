package ru.svlit.coronadash.application.port.out;

import ru.svlit.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.coronadash.domain.World;

public interface FetchCoronavirusDataPort {

    World get() throws CouldNotFetchCoronavirusDataException;
}

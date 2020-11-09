package ru.svlit.feature.coronadash.application.port.out;

import ru.svlit.feature.coronadash.domain.World;

public interface GetPersistedCoronavirusDataPort {
    World get() throws NoPersistedCoronavirusDataException;

    class NoPersistedCoronavirusDataException extends Exception {
    }
}

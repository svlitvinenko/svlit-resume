package ru.svlit.coronadash.application.port.out;

import ru.svlit.coronadash.domain.World;

public interface GetPersistedCoronavirusDataPort {
    World get() throws NoPersistedCoronavirusDataException;

    class NoPersistedCoronavirusDataException extends Exception {
    }
}

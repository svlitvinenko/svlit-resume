package ru.svlit.coronadash.application.port.out;

import ru.svlit.coronadash.domain.World;

public interface PersistCoronavirusDataPort {
    void persist(World world) throws CouldNotPersistCoronavirusDataException;

    class CouldNotPersistCoronavirusDataException extends Exception {
    }
}

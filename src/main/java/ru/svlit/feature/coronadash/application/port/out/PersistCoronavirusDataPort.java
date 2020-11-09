package ru.svlit.feature.coronadash.application.port.out;

import ru.svlit.feature.coronadash.domain.World;

public interface PersistCoronavirusDataPort {
    void persist(World world) throws CouldNotPersistCoronavirusDataException;

    class CouldNotPersistCoronavirusDataException extends Exception {
    }
}

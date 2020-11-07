package ru.svlit.coronadash.adapter.thirdparty;

import ru.svlit.coronadash.domain.World;

import java.time.LocalDate;

public interface CoronavirusThirdPartyRepository {

    World getForDate(LocalDate date) throws CoronavirusDataFetchException;

    class CoronavirusDataFetchException extends Exception {
        public CoronavirusDataFetchException(Exception cause) {
            super(cause);
        }
    }
}

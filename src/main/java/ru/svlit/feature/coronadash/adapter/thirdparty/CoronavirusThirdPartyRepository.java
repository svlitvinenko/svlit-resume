package ru.svlit.feature.coronadash.adapter.thirdparty;

import ru.svlit.feature.coronadash.domain.World;

import java.time.LocalDate;

public interface CoronavirusThirdPartyRepository {

    World getForDate(LocalDate date) throws CoronavirusDataFetchException;

    class CoronavirusDataFetchException extends Exception {
        public CoronavirusDataFetchException(Exception cause) {
            super(cause);
        }
    }
}

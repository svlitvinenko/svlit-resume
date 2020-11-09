package ru.svlit.feature.coronadash.application.port.exception;

public class CouldNotFetchCoronavirusDataException extends Exception {
    public CouldNotFetchCoronavirusDataException(Exception e) {
        super(e);
    }

    public CouldNotFetchCoronavirusDataException() {
        super();
    }
}
package ru.svlit.feature.home.application.port.in;

import ru.svlit.feature.home.application.entity.MessageOfTheDay;

public interface GetMessageOfTheDayUseCase {

    MessageOfTheDay perform() throws CouldNotGetQuoteException;

    class CouldNotGetQuoteException extends Exception {
    }
}

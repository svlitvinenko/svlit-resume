package ru.svlit.entry.home.application.port.in;

import ru.svlit.entry.home.application.entity.MessageOfTheDay;

public interface GetMessageOfTheDayUseCase {

    MessageOfTheDay perform() throws CouldNotGetQuoteException;

    class CouldNotGetQuoteException extends Exception {
    }
}

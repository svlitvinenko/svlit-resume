package ru.svlit.entry.home.application.port.out;

import ru.svlit.entry.home.application.entity.MessageOfTheDay;

public interface GetMessageOfTheDayPort {

    MessageOfTheDay perform() throws CouldNotFetchQuoteException;

    class CouldNotFetchQuoteException extends Exception {}
}

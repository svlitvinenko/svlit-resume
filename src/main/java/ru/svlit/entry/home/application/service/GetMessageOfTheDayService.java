package ru.svlit.entry.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.entry.home.application.entity.MessageOfTheDay;
import ru.svlit.entry.home.application.port.in.GetMessageOfTheDayUseCase;
import ru.svlit.entry.home.application.port.out.GetMessageOfTheDayPort;

@UseCase
@RequiredArgsConstructor
class GetMessageOfTheDayService implements GetMessageOfTheDayUseCase {
    private final GetMessageOfTheDayPort getMessageOfTheDayPort;


    @Override
    public MessageOfTheDay perform() throws CouldNotGetQuoteException {
        try {
            return getMessageOfTheDayPort.perform();
        } catch (GetMessageOfTheDayPort.CouldNotFetchQuoteException e) {
            throw new CouldNotGetQuoteException();
        }
    }
}

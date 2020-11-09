package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.home.application.port.out.GetMessageOfTheDayPort;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;
import ru.svlit.feature.home.application.port.in.GetMessageOfTheDayUseCase;

@UseCase
@RequiredArgsConstructor
class GetMessageOfTheDayService implements GetMessageOfTheDayUseCase {
    private final GetMessageOfTheDayPort getMessageOfTheDayPort;


    @Override
    public MessageOfTheDay perform() {
        return getMessageOfTheDayPort.perform();
    }
}

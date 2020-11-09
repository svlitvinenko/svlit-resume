package ru.svlit.feature.home.adapter.thirdparty;

import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.feature.home.application.port.out.GetMessageOfTheDayPort;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;

@WebAdapter
public class GetMessageOfTheDayPortImpl implements GetMessageOfTheDayPort {
    @Override
    public MessageOfTheDay perform() {
        return new MessageOfTheDay("Азьм езьм бог.", "Я");
    }
}

package ru.svlit.feature.home.application.port.out;

import ru.svlit.feature.home.application.entity.MessageOfTheDay;

public interface GetMessageOfTheDayPort {

    MessageOfTheDay perform();
}

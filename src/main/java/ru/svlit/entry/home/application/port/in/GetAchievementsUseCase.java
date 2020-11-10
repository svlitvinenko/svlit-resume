package ru.svlit.entry.home.application.port.in;

import ru.svlit.entry.home.application.entity.Achievement;

import java.util.List;

public interface GetAchievementsUseCase {
    List<Achievement> perform();
}

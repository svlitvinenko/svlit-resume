package ru.svlit.feature.home.application.port.in;

import ru.svlit.feature.home.application.entity.Achievement;

import java.util.List;

public interface GetAchievementsUseCase {
    List<Achievement> perform();
}

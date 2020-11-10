package ru.svlit.entry.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.entry.home.application.entity.Achievement;
import ru.svlit.entry.home.application.port.in.GetAchievementsUseCase;

import java.util.List;

import static java.util.Arrays.asList;

@UseCase
@RequiredArgsConstructor
public class GetAchievementsService implements GetAchievementsUseCase {

    @Override
    public List<Achievement> perform() {
        return asList(
                new Achievement(
                        "СберБанк Онлайн. Штрафы ГИБДД",
                        "Сервис поиска и оплаты штрафов ГИБДД с уведомлениями о новых начислениях",
                        "/img/achievements/ill_achievement_penalty.jpeg"
                ),
                new Achievement(
                        "СберБанк Онлайн. Счета к оплате и балансы",
                        "MVP-версия продукта «Счета к оплате и балансы», положившая начало становлению объектной модели банка.",
                        "/img/achievements/ill_achievement_penalty.jpeg"
                )
        );
    }
}

package ru.svlit.feature.coronadash.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import ru.svlit.feature.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.feature.coronadash.application.port.in.UpdateCoronavirusDataUseCase;
import ru.svlit.feature.coronadash.application.port.out.FetchCoronavirusDataPort;
import ru.svlit.feature.coronadash.application.port.out.PersistCoronavirusDataPort;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.coronadash.domain.World;
import ru.svlit.feature.coronadash.configuration.CoronaDashConfigurationConstants;

@Slf4j
@UseCase
@RequiredArgsConstructor
class UpdateCoronavirusDataService implements UpdateCoronavirusDataUseCase {
    private final PersistCoronavirusDataPort persistCoronavirusDataPort;
    private final FetchCoronavirusDataPort getCoronavirusDataPort;

    @Override
    @Scheduled(fixedDelayString = CoronaDashConfigurationConstants.SPEL_REFRESH_DELAY_MS)
    public void update() {
        log.info("Инициировано обновление статистики по коронавирусу.");
        World world = null;
        try {
            world = getCoronavirusDataPort.get();
            log.info("Обновленные данные получены успешно.");
        } catch (CouldNotFetchCoronavirusDataException e) {
            log.error("Не удалось извлечь обновленную статистику по коронавирусу.");
        }

        final World fetchedWorld = world;
        if (fetchedWorld == null) {
            return;
        }
        try {
            persistCoronavirusDataPort.persist(fetchedWorld);
            log.info("Обновленные данные сохранены успешно.");
        } catch (PersistCoronavirusDataPort.CouldNotPersistCoronavirusDataException e) {
            log.error("Не удалось сохранить обновлённую статистику по коронавирусу в БД.");
        }

        log.info("Завершено обновление статистики по коронавирусу.");
    }
}

package ru.svlit.coronadash.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import ru.svlit.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.coronadash.application.port.in.UpdateCoronavirusDataUseCase;
import ru.svlit.coronadash.application.port.out.FetchCoronavirusDataPort;
import ru.svlit.coronadash.application.port.out.PersistCoronavirusDataPort;
import ru.svlit.coronadash.application.port.out.PersistCoronavirusDataPort.CouldNotPersistCoronavirusDataException;
import ru.svlit.coronadash.architecture.UseCase;
import ru.svlit.coronadash.domain.World;

import static ru.svlit.coronadash.configuration.CoronaDashConfigurationConstants.SPEL_REFRESH_DELAY_MS;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateCoronavirusDataService implements UpdateCoronavirusDataUseCase {
    private final PersistCoronavirusDataPort persistCoronavirusDataPort;
    private final FetchCoronavirusDataPort getCoronavirusDataPort;

    @Override
    @Scheduled(fixedDelayString = SPEL_REFRESH_DELAY_MS)
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
        } catch (CouldNotPersistCoronavirusDataException e) {
            log.error("Не удалось сохранить обновлённую статистику по коронавирусу в БД.");
        }

        log.info("Завершено обновление статистики по коронавирусу.");
    }
}

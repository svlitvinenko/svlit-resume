package ru.svlit.feature.coronadash.adapter.persistance;

import lombok.RequiredArgsConstructor;
import ru.svlit.feature.coronadash.adapter.persistance.converter.CountryStatsToWorldWideStatisticsConverter;
import ru.svlit.feature.coronadash.adapter.persistance.converter.DomainToPersistenceConverter;
import ru.svlit.feature.coronadash.application.port.out.GetPersistedCoronavirusDataPort;
import ru.svlit.feature.coronadash.application.port.out.PersistCoronavirusDataPort;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.feature.coronadash.domain.World;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
class CountryStatsPersistenceAdapter implements PersistCoronavirusDataPort, GetPersistedCoronavirusDataPort {

    private final CountryStatsRepository countryStatsRepository;
    private final CountryStatsToWorldWideStatisticsConverter countryStatsToWorldWideStatisticsConverter;
    private final DomainToPersistenceConverter domainToPersistenceConverter;

    @Override
    public void persist(World world) {
        countryStatsRepository.deleteAll();
        final List<CountryStat> countryStats = domainToPersistenceConverter.convert(world);
        countryStatsRepository.saveAll(countryStats);
    }

    @Override
    public World get() throws NoPersistedCoronavirusDataException {
        final List<CountryStat> countryStats = countryStatsRepository.findAll();

        if (countryStats.isEmpty()) {
            throw new NoPersistedCoronavirusDataException();
        }

        return countryStatsToWorldWideStatisticsConverter.convert(countryStats);
    }
}

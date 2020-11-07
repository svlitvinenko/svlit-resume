package ru.svlit.coronadash.adapter.persistance;

import lombok.RequiredArgsConstructor;
import ru.svlit.coronadash.adapter.persistance.converter.CountryStatsToWorldWideStatisticsConverter;
import ru.svlit.coronadash.adapter.persistance.converter.DomainToPersistenceConverter;
import ru.svlit.coronadash.application.port.out.GetPersistedCoronavirusDataPort;
import ru.svlit.coronadash.application.port.out.PersistCoronavirusDataPort;
import ru.svlit.coronadash.architecture.PersistenceAdapter;
import ru.svlit.coronadash.domain.World;

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

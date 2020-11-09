package ru.svlit.feature.coronadash.adapter.persistance.converter;

import org.springframework.stereotype.Service;
import ru.svlit.feature.coronadash.adapter.persistance.CountryStat;
import ru.svlit.feature.coronadash.adapter.persistance.CountryStat.RegionStat;
import ru.svlit.feature.coronadash.adapter.persistance.CountryStat.Stat;
import ru.svlit.feature.coronadash.domain.Country;
import ru.svlit.feature.coronadash.domain.Region;
import ru.svlit.feature.coronadash.domain.StatisticsEntry;
import ru.svlit.feature.coronadash.domain.World;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DomainToPersistenceConverter {
    public List<CountryStat> convert(World world) {
        final LocalDateTime now = world.getTimestamp();
        return world.getCountries().stream().map((Country country) -> convertCountry(country, now)).collect(toList());
    }

    private CountryStat convertCountry(Country country, LocalDateTime timestamp) {
        return new CountryStat(
                country.getId(),
                country.getName(),
                country.getRegions().stream().map(this::convertRegion).collect(toList()),
                country.getStatistics().stream().map(this::convertStatistics).collect(toList()),
                timestamp
        );
    }

    private RegionStat convertRegion(Region region) {
        return new RegionStat(
                region.getName(),
                region.getStatistics().stream().map(this::convertStatistics).collect(toList())
        );
    }

    private Stat convertStatistics(StatisticsEntry statisticsEntry) {
        return new Stat(statisticsEntry.getDate(), statisticsEntry.getInfections());
    }
}

package ru.svlit.coronadash.adapter.persistance.converter;

import org.springframework.stereotype.Service;
import ru.svlit.coronadash.adapter.persistance.CountryStat;
import ru.svlit.coronadash.adapter.persistance.CountryStat.RegionStat;
import ru.svlit.coronadash.adapter.persistance.CountryStat.Stat;
import ru.svlit.coronadash.domain.Country;
import ru.svlit.coronadash.domain.Region;
import ru.svlit.coronadash.domain.StatisticsEntry;
import ru.svlit.coronadash.domain.World;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.MIN;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

@Service
public class CountryStatsToWorldWideStatisticsConverter {
    public World convert(List<CountryStat> countryStats) {
        final List<Country> countries = countryStats.stream().map(this::convertCountry).collect(toList());

        final LocalDateTime timestamp;
        if (countryStats.size() > 0) {
            timestamp = countryStats.get(0).getTimestamp();
        } else {
            timestamp = MIN;
        }

        return new World(countries, timestamp);
    }

    private Country convertCountry(CountryStat countryStat) {
        return new Country(
                countryStat.getId(),
                countryStat.getName(),
                countryStat.getRegionStats().stream().map(this::convertRegion).collect(toList()),
                countryStat.getCommonStats().stream().map(this::convertStatistics).collect(toList())
        );
    }

    private Region convertRegion(RegionStat regionStat) {
        return new Region(
                regionStat.getName(),
                regionStat.getStats().stream().map(this::convertStatistics).collect(toList())
        );
    }

    private StatisticsEntry convertStatistics(Stat stat) {
        return new StatisticsEntry(stat.getDate(), stat.getInfected());
    }
}

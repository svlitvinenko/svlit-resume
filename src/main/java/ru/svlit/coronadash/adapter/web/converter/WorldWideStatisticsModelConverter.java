package ru.svlit.coronadash.adapter.web.converter;

import org.springframework.stereotype.Service;
import ru.svlit.coronadash.domain.Country;
import ru.svlit.coronadash.domain.StatisticsEntry;
import ru.svlit.coronadash.domain.World;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

@Service
public class WorldWideStatisticsModelConverter {
    private static final String MODEL_COUNTRY_ROWS = "rows";
    private static final String MODEL_TOTAL_CASES_REPORTED = "totalCasesReported";
    private static final String MODEL_TOTAL_INCREASE = "totalIncrease";

    public Map<String, Object> convert(World statistics) {
        final Map<String, Object> model = new HashMap<>();

        final List<FlatRegionStatisticsRow> countryRows = getCountryRows(statistics).stream()
                .sorted((a, b) -> (int)(b.getIncreasedBy() - a.getIncreasedBy()))
                .collect(toUnmodifiableList());
        model.put(MODEL_COUNTRY_ROWS, countryRows);

        final long totalCasesReported = countryRows.stream().mapToLong(FlatRegionStatisticsRow::getTotalCases).sum();
        model.put(MODEL_TOTAL_CASES_REPORTED, DecimalFormat.getNumberInstance().format(totalCasesReported));

        final long totalIncrease = countryRows.stream().mapToLong(FlatRegionStatisticsRow::getIncreasedBy).sum();
        model.put(MODEL_TOTAL_INCREASE, DecimalFormat.getNumberInstance().format(totalIncrease));

        return model;
    }

    private List<FlatRegionStatisticsRow> getCountryRows(World statistics) {
        return statistics.getCountries().stream()
                .map(country -> new FlatRegionStatisticsRow(
                        country.getName(),
                        (long) country.getStatistics().stream()
                                .map(StatisticsEntry::getInfections)
                                .max(comparingInt(o -> o))
                                .orElse(0),
                        getIncreaseForCountry(country)
                ))
                .collect(toList());
    }

    private long getIncreaseForCountry(Country country) {
        final int statisticsSize = country.getStatistics().size();
        if (statisticsSize < 2) {
            return 0;
        }

        final Optional<StatisticsEntry> newestStatistics = country.getStatistics().stream()
                .max((a, b) -> (int) (a.getDate().toEpochDay() - b.getDate().toEpochDay()));

        final Optional<StatisticsEntry> previousStatistics = country.getStatistics().stream()
                .limit(country.getStatistics().size() - 1)
                .max((a, b) -> (int) (a.getDate().toEpochDay() - b.getDate().toEpochDay()));

        if (newestStatistics.isEmpty() || previousStatistics.isEmpty()) {
            return 0;
        } else {
            return newestStatistics.get().getInfections() - previousStatistics.get().getInfections();
        }
    }
}

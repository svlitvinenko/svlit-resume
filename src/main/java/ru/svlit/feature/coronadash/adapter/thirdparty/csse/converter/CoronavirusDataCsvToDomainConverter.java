package ru.svlit.feature.coronadash.adapter.thirdparty.csse.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.svlit.feature.coronadash.domain.Country;
import ru.svlit.feature.coronadash.domain.Region;
import ru.svlit.feature.coronadash.domain.StatisticsEntry;
import ru.svlit.feature.coronadash.domain.World;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.now;
import static java.util.Collections.unmodifiableList;
import static java.util.UUID.randomUUID;

@Slf4j
@Service
public class CoronavirusDataCsvToDomainConverter {

    private static final String HEADER_REGION = "Province/State";
    private static final String HEADER_COUNTRY = "Country/Region";
    private static final String HEADER_LATITUDE = "Lat";
    private static final String HEADER_LONGITUDE = "Long";
    private static final Pattern HEADER_DATE_PATTERN = Pattern.compile("(\\d+)/(\\d+)/(\\d+)");

    public World convert(String csvString) throws IOException {
        final Reader reader = new StringReader(csvString);
        final CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        final List<String> dateHeaders = getDateHeaders(parser);

        final World world = new World(new ArrayList<>(), now());
        for (CSVRecord record : parser.getRecords()) {
            parseRow(world, record, dateHeaders);
        }

        return world;
    }

    private List<String> getDateHeaders(CSVParser parser) {
        final List<String> headerNames = new ArrayList<>(parser.getHeaderNames());
        headerNames.remove(HEADER_REGION);
        headerNames.remove(HEADER_COUNTRY);
        headerNames.remove(HEADER_LATITUDE);
        headerNames.remove(HEADER_LONGITUDE);
        return unmodifiableList(headerNames);
    }

    private void parseRow(World world,
                          CSVRecord record,
                          List<String> dateHeaders) {
        final String regionName = record.get(HEADER_REGION);
        final String countryName = record.get(HEADER_COUNTRY);
        final List<StatisticsEntry> entries = getStatisticsEntriesForRow(record, dateHeaders);

        createCountryIfNeeded(world, countryName);
        createRegionIfNeeded(world, countryName, regionName);

        if (regionName.isBlank()) {
            findCountryByName(world, countryName).orElseThrow().getStatistics().addAll(entries);
        } else {
            findRegionByCountryNameAndRegionName(world, countryName, regionName).orElseThrow().getStatistics().addAll(entries);
        }
    }

    private void createRegionIfNeeded(World world, String countryName, String regionName) {
        final Optional<Country> countryOptional = findCountryByName(world, countryName);
        final Optional<Region> regionOptional = findRegionByCountryNameAndRegionName(world, countryName, regionName);
        if (regionOptional.isEmpty() && countryOptional.isPresent()) {
            countryOptional.get().getRegions().add(new Region(regionName, new ArrayList<>()));
        }
    }

    private void createCountryIfNeeded(World world, String countryName) {
        final Optional<Country> countryOptional = findCountryByName(world, countryName);
        if (countryOptional.isEmpty()) {
            world.getCountries().add(
                    new Country(randomUUID().toString(), countryName, new ArrayList<>(), new ArrayList<>())
            );
        }
    }

    private Optional<Country> findCountryByName(World world, String countryName) {
        return world.getCountries().stream()
                .filter(c -> c.getName().equalsIgnoreCase(countryName))
                .findFirst();
    }

    private Optional<Region> findRegionByName(Country country, String regionName) {
        return country.getRegions().stream()
                .filter(r -> r.getName().equalsIgnoreCase(regionName))
                .findFirst();
    }

    private Optional<Region> findRegionByCountryNameAndRegionName(World world, String countryName, String regionName) {
        final Optional<Country> countryOptional = findCountryByName(world, countryName);
        if (countryOptional.isEmpty()) {
            return Optional.empty();
        }

        return findRegionByName(countryOptional.get(), regionName);
    }

    private void parseCountry(CSVRecord record, List<String> dateHeaders) {

    }

    private void parseRegion(CSVRecord record, List<String> dateHeaders, String state) {

    }

    private List<StatisticsEntry> getStatisticsEntriesForRow(CSVRecord record, List<String> dateHeaders) {
        final List<StatisticsEntry> statisticsEntryOfRecord = new ArrayList<>();
        for (String dateHeader : dateHeaders) {
            final LocalDate date = parseDateOrNull(dateHeader);
            final Integer amount = parseIntegerOrNull(record.get(dateHeader));
            if (date != null && amount != null) {
                statisticsEntryOfRecord.add(new StatisticsEntry(date, amount));
            }
        }

        return statisticsEntryOfRecord;
    }

    private Integer parseIntegerOrNull(String string) {
        try {
            final Float f = parseFloatOrNull(string);

            if (f == null) {
                return null;
            }

            return f.intValue();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Nullable
    private Float parseFloatOrNull(@Nullable final String string) {
        if (string == null) {
            return null;
        }

        try {
            return parseFloat(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Nullable
    private LocalDate parseDateOrNull(String monthDayYearString) {
        try {
            Matcher matcher = HEADER_DATE_PATTERN.matcher(monthDayYearString);
            if (matcher.find()) {
                final int shortYear = parseInt(matcher.group(3));
                final int month = parseInt(matcher.group(1));
                final int dayOfMonth = parseInt(matcher.group(2));
                final int fullYear = 2000 + shortYear;
                return LocalDate.of(fullYear, month, dayOfMonth);
            }
        } catch (NumberFormatException e) {
            log.error(e.getLocalizedMessage());
        }

        return null;
    }
}

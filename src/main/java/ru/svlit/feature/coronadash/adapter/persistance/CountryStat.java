package ru.svlit.feature.coronadash.adapter.persistance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Document(collection = "countries")
public class CountryStat {
    @Id
    private final String id;
    private final String name;
    private final List<RegionStat> regionStats;
    private final List<Stat> commonStats;
    private final LocalDateTime timestamp;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static class RegionStat {
        private final String name;
        private final List<Stat> stats;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static class Stat {
        private final LocalDate date;
        private final int infected;
    }
}

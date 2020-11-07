package ru.svlit.coronadash.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Country {
    private final String id;
    private final String name;
    private final List<Region> regions;
    private final List<StatisticsEntry> statistics;
}

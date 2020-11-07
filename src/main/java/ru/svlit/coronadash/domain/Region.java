package ru.svlit.coronadash.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Region {
    private final String name;
    private final List<StatisticsEntry> statistics;
}

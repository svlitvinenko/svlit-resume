package ru.svlit.feature.coronadash.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class StatisticsEntry {
    private final LocalDate date;
    private final int infections;
}

package ru.svlit.feature.coronadash.adapter.web.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class FlatRegionStatisticsRow {
    private final String country;
    private final Long totalCases;
    private final Long increasedBy;
}

package ru.svlit.entry.home.application.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Achievement {
    private final String title;
    private final String description;
    private final String imageSource;
}

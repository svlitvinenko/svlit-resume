package ru.svlit.feature.home.application.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class MessageOfTheDay {
    private final String message;
    private final String author;
}

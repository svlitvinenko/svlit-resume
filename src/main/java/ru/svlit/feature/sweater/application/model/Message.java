package ru.svlit.feature.sweater.application.model;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Message {
    private final String id;
    private final String text;
    private final String tag;
    private final String author;
}

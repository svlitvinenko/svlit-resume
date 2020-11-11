package ru.svlit.feature.sweater.application.model;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Message {
    private Integer id;
    private final String text;
    private final String tag;
}

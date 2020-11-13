package ru.svlit.feature.sweater.application.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.feature.authentication.domain.User;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Message {
    private final String id;
    private final String text;
    private final String tag;
    private final User author;
}

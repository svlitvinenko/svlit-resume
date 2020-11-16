package ru.svlit.feature.wall.adapter.persistence.datasource.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("messages")
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class MessageModel {
    @Id
    private final String id;
    private final String text;
    private final String tag;
    private final String authorId;
}

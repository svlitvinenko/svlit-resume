package ru.svlit.feature.sweater.adapter.persistence.datasource.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class MessageModel {
    private final String id;
    private final String text;
    private final String tag;
    private final String author;
}

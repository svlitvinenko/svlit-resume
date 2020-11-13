package ru.svlit.feature.home.application.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class SocialNetwork {
    private final String name;
    private final String iconUrl;
    private final String baseUrl;
}

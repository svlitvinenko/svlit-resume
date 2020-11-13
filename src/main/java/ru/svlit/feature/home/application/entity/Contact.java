package ru.svlit.feature.home.application.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Contact {
    private final String visibleName;
    private final String userIdentifier;
    private final SocialNetwork socialNetwork;
}

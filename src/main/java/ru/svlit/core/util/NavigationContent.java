package ru.svlit.core.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.feature.home.application.entity.Contact;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class NavigationContent {
    private final List<EntryPointDescription> features;
    private final List<Contact> contacts;
    private final UserInfo userInfo;
}

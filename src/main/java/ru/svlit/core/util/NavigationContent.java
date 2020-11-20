package ru.svlit.core.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.feature.home.application.entity.Contact;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class NavigationContent {

    private final HeaderContent headerContent;
    private final FooterContent footerContent;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static final class HeaderContent {
        private final String textTitle;
        private final String textFeatures;
        private final String textContacts;
        private final String textSignIn;
        private final String textSignOut;
        private final String textSignUp;
        private final List<EntryPoint> features;
        private final List<Contact> contacts;
        private final UserInfo userInfo;

        @Getter
        @ToString
        @EqualsAndHashCode
        @RequiredArgsConstructor
        public static final class EntryPoint {
            private final String relativeAddress;
            private final String title;
        }
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static final class FooterContent {
        private final LocaleDescription selectedLocale;
        private final List<LocaleDescription> availableLocales;

        @Getter
        @ToString
        @EqualsAndHashCode
        @RequiredArgsConstructor
        public static class LocaleDescription {
            private final String link;
            private final String name;
        }
    }
}

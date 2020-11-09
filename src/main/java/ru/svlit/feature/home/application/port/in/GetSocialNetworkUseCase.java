package ru.svlit.feature.home.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.feature.home.application.entity.SocialNetworkType;
import ru.svlit.feature.home.application.entity.SocialNetwork;

public interface GetSocialNetworkUseCase {

    SocialNetwork perform(GetSocialNetworkCommand command) throws UnsupportedSocialNetworkException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    final class GetSocialNetworkCommand {
        private final SocialNetworkType socialNetworkType;
    }

    class UnsupportedSocialNetworkException extends Exception {
    }
}

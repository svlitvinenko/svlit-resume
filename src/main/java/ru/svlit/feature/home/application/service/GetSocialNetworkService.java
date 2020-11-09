package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.home.application.entity.SocialNetwork;
import ru.svlit.feature.home.application.port.in.GetSocialNetworkUseCase;
import ru.svlit.feature.home.configuration.HomeConfiguration;

@UseCase
@RequiredArgsConstructor
class GetSocialNetworkService implements GetSocialNetworkUseCase {

    private final HomeConfiguration configuration;

    @Override
    public SocialNetwork perform(GetSocialNetworkCommand command) throws UnsupportedSocialNetworkException {
        return switch (command.getSocialNetworkType()) {
            case TELEGRAM -> telegram();
            case VK -> vk();
        };
    }

    private SocialNetwork telegram() {
        return new SocialNetwork(
                configuration.getTelegramName(),
                configuration.getTelegramIconUrl(),
                configuration.getTelegramBaseUrl()
        );
    }

    private SocialNetwork vk() throws UnsupportedSocialNetworkException {
        throw new UnsupportedSocialNetworkException();
    }
}

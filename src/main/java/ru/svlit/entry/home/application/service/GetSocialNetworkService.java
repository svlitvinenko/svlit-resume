package ru.svlit.entry.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.entry.home.application.entity.SocialNetwork;
import ru.svlit.entry.home.application.port.in.GetSocialNetworkUseCase;
import ru.svlit.entry.home.configuration.HomeConfiguration;

@UseCase
@RequiredArgsConstructor
class GetSocialNetworkService implements GetSocialNetworkUseCase {

    private final HomeConfiguration configuration;

    @Override
    public SocialNetwork perform(GetSocialNetworkCommand command) throws UnsupportedSocialNetworkException {
        return switch (command.getSocialNetworkType()) {
            case TELEGRAM -> telegram();
            case VK -> vk();
            case EMAIL -> email();
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

    private SocialNetwork email() {
        return new SocialNetwork(
                "Email",
                configuration.getEmailIconUrl(),
                "mailto:"
        );
    }
}

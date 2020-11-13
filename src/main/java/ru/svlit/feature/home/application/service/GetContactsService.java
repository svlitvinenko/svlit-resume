package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.home.application.entity.Contact;
import ru.svlit.feature.home.application.entity.SocialNetwork;
import ru.svlit.feature.home.application.port.in.GetContactsUseCase;
import ru.svlit.feature.home.application.port.in.GetSocialNetworkUseCase;
import ru.svlit.feature.home.configuration.HomeConfiguration;
import ru.svlit.feature.home.application.entity.SocialNetworkType;

import java.util.List;

import static java.util.Arrays.asList;

@Slf4j
@UseCase
@RequiredArgsConstructor
class GetContactsService implements GetContactsUseCase {

    private final GetSocialNetworkUseCase getSocialNetworkUseCase;
    private final HomeConfiguration configuration;

    @Override
    public List<Contact> perform() {
        final Contact telegramContact = getTelegramOrNull();
        final Contact emailContact = getEmailOrNull();
        return asList(telegramContact, emailContact);
    }

    private Contact getTelegramOrNull() {
        try {
            final SocialNetwork telegram = getSocialNetworkUseCase.perform(new GetSocialNetworkUseCase.GetSocialNetworkCommand(SocialNetworkType.TELEGRAM));
            return new Contact(
                    configuration.getTelegramVisibleName(),
                    configuration.getTelegramUserIdentifier(),
                    telegram
            );
        } catch (GetSocialNetworkUseCase.UnsupportedSocialNetworkException e) {
            log.error("Could not get social network: " + SocialNetworkType.TELEGRAM);
            return null;
        }
    }

    private Contact getEmailOrNull() {
        try {
            final SocialNetwork email = getSocialNetworkUseCase.perform(new GetSocialNetworkUseCase.GetSocialNetworkCommand(SocialNetworkType.EMAIL));
            return new Contact(
                    email.getName(),
                    configuration.getEmailUserIdentifier(),
                    email
            );
        } catch (GetSocialNetworkUseCase.UnsupportedSocialNetworkException e) {
            log.error("Could not get social network: " + SocialNetworkType.EMAIL);
            return null;
        }
    }
}

package ru.svlit.entry.home.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.entry.home.application.entity.Contact;
import ru.svlit.entry.home.application.entity.SocialNetwork;
import ru.svlit.entry.home.application.port.in.GetContactsUseCase;
import ru.svlit.entry.home.application.port.in.GetSocialNetworkUseCase;
import ru.svlit.entry.home.application.port.in.GetSocialNetworkUseCase.GetSocialNetworkCommand;
import ru.svlit.entry.home.configuration.HomeConfiguration;

import java.util.List;

import static java.util.Arrays.asList;
import static ru.svlit.entry.home.application.entity.SocialNetworkType.EMAIL;
import static ru.svlit.entry.home.application.entity.SocialNetworkType.TELEGRAM;

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
            final SocialNetwork telegram = getSocialNetworkUseCase.perform(new GetSocialNetworkCommand(TELEGRAM));
            return new Contact(
                    configuration.getTelegramVisibleName(),
                    configuration.getTelegramUserIdentifier(),
                    telegram
            );
        } catch (GetSocialNetworkUseCase.UnsupportedSocialNetworkException e) {
            log.error("Could not get social network: " + TELEGRAM);
            return null;
        }
    }

    private Contact getEmailOrNull() {
        try {
            final SocialNetwork email = getSocialNetworkUseCase.perform(new GetSocialNetworkCommand(EMAIL));
            return new Contact(
                    email.getName(),
                    configuration.getEmailUserIdentifier(),
                    email
            );
        } catch (GetSocialNetworkUseCase.UnsupportedSocialNetworkException e) {
            log.error("Could not get social network: " + EMAIL);
            return null;
        }
    }
}

package ru.svlit.feature.authentication.adapter.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.svlit.core.global.configuration.GlobalConfigurationProperties;
import ru.svlit.feature.authentication.application.port.out.SendActivationCodePort;
import ru.svlit.feature.authentication.configuration.MailConfigurationProperties;

/**
 * Отправитель почтовых сообщений с предложением активации аккаунта по ссылке.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Component
@RequiredArgsConstructor
public class ActivationCodeSender implements SendActivationCodePort {

    private final JavaMailSender mailSender;
    private final MailConfigurationProperties mailConfigurationProperties;
    private final GlobalConfigurationProperties globalConfigurationProperties;

    @Override
    public void send(ActivationCommand command) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailConfigurationProperties.getUsername());
        message.setTo(command.getEmailTo());
        message.setSubject("Activation code");

        final String activationLink = getActivationLink(command.getActivationCode().getActivationCode());
        final String text = "Hello " + command.getUsername()
                + "! Welcome to my service. Here's your activation link: " + activationLink + ".";

        message.setText(text);
        mailSender.send(message);
    }

    private String getActivationLink(String activationCode) {
        return globalConfigurationProperties.getBaseUrl() + "/activation/" + activationCode;
    }
}

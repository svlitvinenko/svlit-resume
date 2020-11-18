package ru.svlit.feature.authentication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Конфигурация почты.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Configuration
@RequiredArgsConstructor
class MailConfiguration {

    private final MailConfigurationProperties configurationProperties;

    @Bean
    public JavaMailSender mailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(configurationProperties.getHost());
        mailSender.setPort(configurationProperties.getPort());
        mailSender.setUsername(configurationProperties.getUsername());
        mailSender.setPassword(configurationProperties.getPassword());
        final Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", configurationProperties.getProtocol());
        properties.setProperty("mail.debug", configurationProperties.getDebug());
        return mailSender;
    }
}

package ru.svlit.feature.authentication.configuration;

/**
 * Свойства почты.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
public interface MailConfigurationProperties {
    String getHost();

    String getUsername();

    String getPassword();

    Integer getPort();

    String getProtocol();

    String getDebug();
}

package ru.svlit.feature.authentication.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Свойства почты.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "authentication.mail")
public class MailConfigurationPropertiesImpl implements MailConfigurationProperties {

    private String host;
    private String username;
    private String password;
    private Integer port;
    private String protocol;
    private String debug;

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Integer getPort() {
        return port;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getDebug() {
        return debug;
    }
}

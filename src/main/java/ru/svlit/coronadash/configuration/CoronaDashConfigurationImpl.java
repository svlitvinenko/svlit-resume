package ru.svlit.coronadash.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static java.lang.String.format;
import static ru.svlit.coronadash.configuration.CoronaDashConfigurationConstants.CONFIGURATION_PREFIX;

@Data
@Configuration
@EnableScheduling
@NoArgsConstructor
@ConfigurationProperties(prefix = CONFIGURATION_PREFIX)
class CoronaDashConfigurationImpl implements CoronaDashConfiguration {
    private String sourceUrl;
    private Long refreshDelayMs;
    private String databaseProtocol;
    private String databaseHost;
    private String databaseUser;
    private String databasePassword;
    private String databaseName;

    @Override
    public String getDataBaseConnectionString() {
        final String template = "%s://%s:%s@%s/%s";
        return format(template, databaseProtocol, databaseUser, databasePassword, databaseHost, databaseName);
    }
}

package ru.svlit.feature.coronadash.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static ru.svlit.feature.coronadash.configuration.CoronaDashConfigurationConstants.CONFIGURATION_PREFIX;

@Data
@Slf4j
@Configuration
@EnableScheduling
@NoArgsConstructor
@ConfigurationProperties(prefix = CONFIGURATION_PREFIX)
class CoronaDashConfigurationImpl implements CoronaDashConfiguration {
    private String sourceUrl;
    private String databaseConnectionString;
    private Long refreshDelayMs;

    @Override
    public String getDatabaseConnectionString() {
        return databaseConnectionString;
    }
}

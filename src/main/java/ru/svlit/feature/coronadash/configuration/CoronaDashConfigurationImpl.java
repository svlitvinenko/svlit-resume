package ru.svlit.feature.coronadash.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static java.lang.String.format;
import static java.lang.System.getenv;
import static ru.svlit.feature.coronadash.configuration.CoronaDashConfigurationConstants.*;

@Data
@Slf4j
@Configuration
@EnableScheduling
@NoArgsConstructor
@ConfigurationProperties(prefix = CONFIGURATION_PREFIX)
class CoronaDashConfigurationImpl implements CoronaDashConfiguration {
    private String sourceUrl;
    private Long refreshDelayMs;

    @Override
    public String getDataBaseConnectionString() {
        String databaseProtocol = getenv(ENV_CD_DATABASE_PROTOCOL);
        String databaseHost = getenv(ENV_CD_DATABASE_HOST);
        String databaseUser = getenv(ENV_CD_DATABASE_USER);
        String databasePassword = getenv(ENV_CD_DATABASE_PASSWORD);
        String databaseName = getenv(ENV_CD_DATABASE_NAME);
        final String template = "%s://%s:%s@%s/%s";
        final String connectionString = format(
                template,
                databaseProtocol,
                databaseUser,
                databasePassword,
                databaseHost,
                databaseName
        );

        log.info("Адрес БД вычислен: " + connectionString);
        return connectionString;
    }
}

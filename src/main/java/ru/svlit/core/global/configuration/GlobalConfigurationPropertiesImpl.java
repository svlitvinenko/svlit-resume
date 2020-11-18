package ru.svlit.core.global.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Глобальные свойства проекта.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "global")
class GlobalConfigurationPropertiesImpl implements GlobalConfigurationProperties {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}

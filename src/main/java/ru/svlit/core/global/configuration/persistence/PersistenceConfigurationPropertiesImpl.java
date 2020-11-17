package ru.svlit.core.global.configuration.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "global")
class PersistenceConfigurationPropertiesImpl implements PersistenceConfigurationProperties {
    private String databaseConnectionString;
}

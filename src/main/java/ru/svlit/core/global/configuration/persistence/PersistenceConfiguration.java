package ru.svlit.core.global.configuration.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PersistenceConfiguration {

    private final PersistenceConfigurationProperties persistenceConfigurationProperties;

    @Bean
    MongoClient provideMongoClient() {
        return MongoClients.create(persistenceConfigurationProperties.getDatabaseConnectionString());
    }
}

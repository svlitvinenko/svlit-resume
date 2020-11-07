package ru.svlit.coronadash.configuration.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.svlit.coronadash.configuration.CoronaDashConfiguration;

@Configuration
@RequiredArgsConstructor
public class PersistenceConfiguration {

    private final CoronaDashConfiguration configuration;

    @Bean
    MongoClient provideMongoClient() {
        final String connectionString = configuration.getDataBaseConnectionString();
        return MongoClients.create(connectionString);
    }
}

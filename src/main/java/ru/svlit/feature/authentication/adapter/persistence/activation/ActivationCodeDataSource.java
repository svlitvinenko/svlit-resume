package ru.svlit.feature.authentication.adapter.persistence.activation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Источник данных о кодах активации аккаунтов.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Repository
public interface ActivationCodeDataSource extends MongoRepository<ActivationCodeModel, String> {
}

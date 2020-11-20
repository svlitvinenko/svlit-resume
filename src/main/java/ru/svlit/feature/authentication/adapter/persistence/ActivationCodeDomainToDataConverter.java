package ru.svlit.feature.authentication.adapter.persistence;

import org.springframework.stereotype.Service;
import ru.svlit.feature.authentication.domain.ActivationCode;

/**
 * Конвертер кодов активации аккаунтов из доменной модели в модель MongoDB.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Service
class ActivationCodeDomainToDataConverter {

    ActivationCodeModel convert(ActivationCode activationCode) {
        return new ActivationCodeModel(
                activationCode.getActivationCode(),
                activationCode.getUserId()
        );
    }
}

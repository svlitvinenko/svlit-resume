package ru.svlit.feature.authentication.adapter.persistence;

import org.springframework.stereotype.Service;
import ru.svlit.feature.authentication.domain.ActivationCode;

/**
 * Конвертер кодов активации аккаунтов из модели MongoDB в доменную модель.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@Service
class ActivationCodeDataToDomainConverter {

    ActivationCode convert(ActivationCodeModel activationCode) {
        return new ActivationCode(
                activationCode.getActivationCode(),
                activationCode.getUserId()
        );
    }
}

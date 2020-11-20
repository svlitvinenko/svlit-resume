package ru.svlit.feature.authentication.adapter.persistence;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.feature.authentication.application.port.out.FindActivationCodePort;
import ru.svlit.feature.authentication.application.port.out.RemoveActivationCodePort;
import ru.svlit.feature.authentication.application.port.out.StoreActivationCodePort;
import ru.svlit.feature.authentication.domain.ActivationCode;

import java.util.Optional;

/**
 * Репозиторий кодов активации аккаунтов.
 *
 * @author Sergei Litvinenko on 18.11.2020.
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class ActivationCodeRepository implements FindActivationCodePort, RemoveActivationCodePort, StoreActivationCodePort {

    private final ActivationCodeDataSource dataSource;
    private final ActivationCodeDataToDomainConverter dataToDomainConverter;
    private final ActivationCodeDomainToDataConverter domainToDataConverter;

    @Override
    public Optional<ActivationCode> findActivationCode(String activationCode) {
        return dataSource.findById(activationCode).map(dataToDomainConverter::convert);
    }

    @Override
    public void remove(ActivationCode activationCode) {
        dataSource.deleteById(activationCode.getActivationCode());
    }

    @Override
    public void store(ActivationCode activationCode) {
        final ActivationCodeModel model = domainToDataConverter.convert(activationCode);
        dataSource.save(model);
    }
}

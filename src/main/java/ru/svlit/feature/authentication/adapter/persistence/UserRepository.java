package ru.svlit.feature.authentication.adapter.persistence;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.feature.authentication.application.port.out.FindUserByIdPort;
import ru.svlit.feature.authentication.application.port.out.FindUserByUsernamePort;
import ru.svlit.feature.authentication.application.port.out.SignUpPort;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserRepository implements SignUpPort, FindUserByUsernamePort, FindUserByIdPort {

    private final UserDataSource userDataSource;
    private final UserDomainToDataConverter userDomainToDataConverter;
    private final UserDataToDomainConverter userDataToDomainConverter;

    @Override
    public void signUp(User user) {
        final UserModel userModel = userDomainToDataConverter.convert(user);
        userDataSource.save(userModel);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        final Optional<UserModel> userOptional = userDataSource.findByUsername(username);
        return userOptional.map(userDataToDomainConverter::convert);
    }

    @Override
    public Optional<User> findById(String id) {
        final Optional<UserModel> userOptional = userDataSource.findById(id);
        return userOptional.map(userDataToDomainConverter::convert);
    }
}

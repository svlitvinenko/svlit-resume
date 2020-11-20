package ru.svlit.core.user.adapter.persistence;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.annotation.PersistenceAdapter;
import ru.svlit.core.user.application.port.out.*;
import ru.svlit.feature.authentication.application.port.out.*;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserRepository implements StoreUserPort, FindUserByUsernamePort, FindUserByIdPort, GetAllUsersPort, UpdateUserPort, RemoveUserByIdPort, ActivateUserPort {

    private final UserDataSource userDataSource;
    private final UserDomainToDataConverter userDomainToDataConverter;
    private final UserDataToDomainConverter userDataToDomainConverter;

    @Override
    public void store(User user) {
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

    @Override
    public Iterable<User> getAllUsers() {
        return userDataSource.findAll().stream().map(userDataToDomainConverter::convert).collect(toList());
    }

    @Override
    public void update(User user) {
        final UserModel userModel = userDomainToDataConverter.convert(user);
        userDataSource.deleteById(userModel.getId());
        userDataSource.save(userModel);
    }

    @Override
    public void removeById(String id) {
        userDataSource.deleteById(id);
    }

    @Override
    public void activate(User user) {
        update(user.withActive(true));
    }
}

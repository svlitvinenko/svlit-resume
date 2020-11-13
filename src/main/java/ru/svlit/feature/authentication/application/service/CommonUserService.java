package ru.svlit.feature.authentication.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.authentication.adapter.persistence.UserDataToDomainConverter;
import ru.svlit.feature.authentication.adapter.persistence.UserModel;
import ru.svlit.feature.authentication.adapter.persistence.UserDataSource;
import ru.svlit.feature.authentication.application.port.in.FindUserByIdUseCase;
import ru.svlit.feature.authentication.application.port.in.FindUserByUsernameUseCase;
import ru.svlit.feature.authentication.application.port.in.GetCurrentUserUseCase;
import ru.svlit.feature.authentication.application.port.out.FindUserByIdPort;
import ru.svlit.feature.authentication.application.port.out.FindUserByUsernamePort;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class CommonUserService implements GetCurrentUserUseCase, FindUserByUsernameUseCase, FindUserByIdUseCase, UserDetailsService {

    private final FindUserByUsernamePort findUserByUsernamePort;
    private final FindUserByIdPort findUserByIdPort;
    private final UserDataSource userDataSource;
    private final UserDataToDomainConverter userDataToDomainConverter;

    @Override
    public Optional<User> getCurrentUser() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return Optional.of(((User) principal));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return findUserByUsernamePort.findByUsername(username);
    }

    @Override
    public Optional<User> findByUd(String id) {
        return findUserByIdPort.findById(id);
    }
}

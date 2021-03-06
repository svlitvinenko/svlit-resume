package ru.svlit.core.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.core.user.application.port.in.FindUserByIdUseCase;
import ru.svlit.core.user.application.port.in.FindUserByUsernameUseCase;
import ru.svlit.core.user.application.port.in.GetCurrentUserUseCase;
import ru.svlit.core.user.application.port.out.FindUserByIdPort;
import ru.svlit.core.user.application.port.out.FindUserByUsernamePort;
import ru.svlit.feature.authentication.domain.User;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
class CommonUserService implements GetCurrentUserUseCase, FindUserByUsernameUseCase, FindUserByIdUseCase, UserDetailsService {

    private final FindUserByUsernamePort findUserByUsernamePort;
    private final FindUserByIdPort findUserByIdPort;

    @Override
    public Optional<User> getCurrentUser() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return findByUsername(((User) principal).getUsername());
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

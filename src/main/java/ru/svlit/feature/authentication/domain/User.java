package ru.svlit.feature.authentication.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.svlit.core.global.configuration.security.Role;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.emptySet;

@With
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class User implements UserDetails {

    private final String id;
    private final String username;
    private final String email;
    private final String password;
    private final Set<Role> roles;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public static User unknown() {
        return new User(
                "-1",
                null,
                "UFO",
                UUID.randomUUID().toString(),
                emptySet(),
                false
        );
    }
}

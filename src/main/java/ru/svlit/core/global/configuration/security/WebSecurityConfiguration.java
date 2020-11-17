package ru.svlit.core.global.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.svlit.core.global.configuration.security.paths.SecuredPathProvider;
import ru.svlit.core.global.configuration.security.paths.SignInPathProvider;
import ru.svlit.core.global.configuration.security.paths.UnsecuredPathProvider;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String DEFAULT_URL = "/";

    private final UserDetailsService userService;
    private final List<SecuredPathProvider> securedPathProviders;
    private final List<UnsecuredPathProvider> unsecuredPathProviders;
    private final SignInPathProvider signInPathProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        setupAuthentication(http);
        setupSignInPath(http);
        setupLogoutAccessibleToEveryone(http);
    }

    private void setupAuthentication(HttpSecurity http) throws Exception {
        final String[] unsecuredPaths = unsecuredPathProviders
                .stream()
                .map(UnsecuredPathProvider::getPathPattern)
                .toArray(String[]::new);
        var registry = http
                .authorizeRequests()
                .antMatchers(unsecuredPaths)
                .permitAll();

        for (var provider : securedPathProviders) {
            final String[] roles = provider.getRoles().stream().map(Role::name).toArray(String[]::new);
            registry = registry.antMatchers(provider.getPathPattern()).hasAnyAuthority(roles);
        }

        registry.anyRequest().authenticated();
    }

    private void setupSignInPath(HttpSecurity http) throws Exception {
        final String signInPath = signInPathProvider.getSignInPath();
        http.formLogin()
                .loginPage(signInPath)
                .defaultSuccessUrl(DEFAULT_URL)
                .permitAll();
    }

    private void setupLogoutAccessibleToEveryone(HttpSecurity http) throws Exception {
        http.logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}

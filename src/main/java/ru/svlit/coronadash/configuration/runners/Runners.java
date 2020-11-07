package ru.svlit.coronadash.configuration.runners;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.svlit.coronadash.application.port.in.GetCoronavirusDataUseCase;

@Configuration
@RequiredArgsConstructor
public class Runners {

    private final GetCoronavirusDataUseCase getCoronavirusDataUseCase;


}

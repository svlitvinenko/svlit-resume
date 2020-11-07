package ru.svlit.coronadash.adapter.thirdparty.csse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.svlit.coronadash.adapter.thirdparty.csse.converter.CoronavirusDataCsvToDomainConverter;
import ru.svlit.coronadash.application.port.exception.CouldNotFetchCoronavirusDataException;
import ru.svlit.coronadash.application.port.out.FetchCoronavirusDataPort;
import ru.svlit.coronadash.configuration.CoronaDashConfiguration;
import ru.svlit.coronadash.domain.World;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
@RequiredArgsConstructor
class CoronavirusCsseRepository implements FetchCoronavirusDataPort {

    private final CoronaDashConfiguration configuration;
    private final CoronavirusDataCsvToDomainConverter csvToDomainConverter;

    @Override
    public World get() throws CouldNotFetchCoronavirusDataException {
        final String csseDataUrl = configuration.getSourceUrl();
        HttpClient httpClient = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(csseDataUrl))
                .build();

        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return csvToDomainConverter.convert(response.body());
        } catch (IOException | InterruptedException e) {
            log.error(e.getLocalizedMessage());
            throw new CouldNotFetchCoronavirusDataException(e);
        }
    }
}

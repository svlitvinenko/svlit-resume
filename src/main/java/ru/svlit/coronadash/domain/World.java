package ru.svlit.coronadash.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class World {
    private final List<Country> countries;
    private final LocalDateTime timestamp;
}

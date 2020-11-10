package ru.svlit.entry.home.application.port.in;

import ru.svlit.architecture.EntryPointDescription;

import java.util.List;

public interface GetEntryPointsUseCase {
    List<EntryPointDescription> perform();
}

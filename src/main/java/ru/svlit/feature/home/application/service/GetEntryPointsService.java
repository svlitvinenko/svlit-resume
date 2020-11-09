package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.home.application.port.in.GetEntryPointsUseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
class GetEntryPointsService implements GetEntryPointsUseCase {

    private final List<EntryPointDescription> entryPointDescriptions;

    @Override
    public List<EntryPointDescription> perform() {
        return entryPointDescriptions;
    }
}

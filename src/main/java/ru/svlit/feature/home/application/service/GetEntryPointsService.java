package ru.svlit.feature.home.application.service;

import lombok.RequiredArgsConstructor;
import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.UseCase;
import ru.svlit.feature.home.application.port.in.GetEntryPointsUseCase;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UseCase
@RequiredArgsConstructor
class GetEntryPointsService implements GetEntryPointsUseCase {

    private final List<EntryPointDescription> entryPointDescriptions;

    @Override
    public List<EntryPointDescription> perform() {
        return entryPointDescriptions.stream().filter(EntryPointDescription::isEnabled).collect(toList());
    }
}

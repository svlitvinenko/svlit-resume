package ru.svlit.feature.wall.configuration;

import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.EntryPoint;

@EntryPoint
class WallEntryPointDescription implements EntryPointDescription {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getTitle() {
        return "The Wall";
    }

    @Override
    public String getRelativeAddress() {
        return "/wall";
    }
}

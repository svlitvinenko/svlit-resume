package ru.svlit.feature.sweater.configuration;

import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.EntryPoint;

@EntryPoint
class SweaterEntryPointDescription implements EntryPointDescription {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getTitle() {
        return "Sweater";
    }

    @Override
    public String getRelativeAddress() {
        return "/sweater";
    }
}

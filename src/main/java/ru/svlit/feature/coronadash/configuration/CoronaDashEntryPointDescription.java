package ru.svlit.feature.coronadash.configuration;

import ru.svlit.architecture.annotation.EntryPoint;
import ru.svlit.architecture.EntryPointDescription;

@EntryPoint
public class CoronaDashEntryPointDescription implements EntryPointDescription {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getTitle() {
        return "COVID-19 Dashboard";
    }

    @Override
    public String getRelativeAddress() {
        return "/covid";
    }
}

package ru.svlit.feature.coronadash.configuration;

import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.EntryPoint;

import static ru.svlit.feature.coronadash.configuration.CoronaDashConfigurationConstants.PATH_SEGMENT_COVID;

@EntryPoint
class CoronaDashEntryPointDescription implements EntryPointDescription {
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
        return PATH_SEGMENT_COVID;
    }
}

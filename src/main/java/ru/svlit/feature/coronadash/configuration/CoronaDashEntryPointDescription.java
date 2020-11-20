package ru.svlit.feature.coronadash.configuration;

import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.EntryPoint;
import ru.svlit.core.globalization.Resource;
import ru.svlit.core.globalization.Resource.FeatureCovidTitleResource;

import static ru.svlit.feature.coronadash.configuration.CoronaDashConfigurationConstants.PATH_SEGMENT_COVID;

@EntryPoint
class CoronaDashEntryPointDescription implements EntryPointDescription {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Resource getTitle() {
        return new FeatureCovidTitleResource();
    }

    @Override
    public String getRelativeAddress() {
        return PATH_SEGMENT_COVID;
    }
}

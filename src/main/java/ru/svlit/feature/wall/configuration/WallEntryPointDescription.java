package ru.svlit.feature.wall.configuration;

import ru.svlit.architecture.EntryPointDescription;
import ru.svlit.architecture.annotation.EntryPoint;
import ru.svlit.core.globalization.Resource;
import ru.svlit.core.globalization.Resource.FeatureWallTitleResource;

import static ru.svlit.feature.wall.configuration.WallConfigurationConstants.PATH_WALL;

@EntryPoint
class WallEntryPointDescription implements EntryPointDescription {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Resource getTitle() {
        return new FeatureWallTitleResource();
    }

    @Override
    public String getRelativeAddress() {
        return PATH_WALL;
    }
}

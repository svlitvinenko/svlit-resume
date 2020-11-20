package ru.svlit.architecture;

import ru.svlit.core.globalization.Resource;

public interface EntryPointDescription {
    boolean isEnabled();

    Resource getTitle();

    String getRelativeAddress();
}

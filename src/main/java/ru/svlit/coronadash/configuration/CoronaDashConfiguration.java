package ru.svlit.coronadash.configuration;

public interface CoronaDashConfiguration {

    String getSourceUrl();

    Long getRefreshDelayMs();

    String getDataBaseConnectionString();
}

package ru.svlit.feature.coronadash.configuration;

public interface CoronaDashConfiguration {

    String getSourceUrl();

    Long getRefreshDelayMs();

    String getDatabaseConnectionString();
}

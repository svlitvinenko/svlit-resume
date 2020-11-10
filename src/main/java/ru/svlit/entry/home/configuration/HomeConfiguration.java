package ru.svlit.entry.home.configuration;

public interface HomeConfiguration {
    String getTelegramName();
    String getTelegramIconUrl();
    String getTelegramBaseUrl();
    String getTelegramUserIdentifier();
    String getTelegramVisibleName();

    String getEmailIconUrl();
    String getEmailUserIdentifier();

    String getQuotesApiBaseUrl();
}

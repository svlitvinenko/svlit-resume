package ru.svlit.entry.home.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static ru.svlit.entry.home.configuration.HomeConfigurationConstants.CONFIGURATION_PREFIX;

@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(CONFIGURATION_PREFIX)
class HomeConfigurationImpl implements HomeConfiguration {
    private String telegramName;
    private String telegramIconUrl;
    private String telegramBaseUrl;
    private String telegramUserIdentifier;
    private String telegramVisibleName;
    private String quotesApiBaseUrl;
    private String emailIconUrl;
    private String emailUserIdentifier;

    @Override
    public String getTelegramName() {
        return telegramName;
    }

    @Override
    public String getTelegramIconUrl() {
        return telegramIconUrl;
    }

    @Override
    public String getTelegramBaseUrl() {
        return telegramBaseUrl;
    }

    @Override
    public String getTelegramUserIdentifier() {
        return telegramUserIdentifier;
    }

    @Override
    public String getTelegramVisibleName() {
        return telegramVisibleName;
    }

    @Override
    public String getEmailIconUrl() {
        return emailIconUrl;
    }

    @Override
    public String getEmailUserIdentifier() {
        return emailUserIdentifier;
    }

    @Override
    public String getQuotesApiBaseUrl() {
        return quotesApiBaseUrl;
    }
}

package ru.svlit.core.globalization.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * Настройки менеджера ресурсов.
 *
 * @author Sergei Litvinenko on 19.11.2020.
 */
@Configuration
class ResourceManagerConfiguration {

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:text");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(Locale.forLanguageTag("en-US"));
        return messageSource;
    }
}

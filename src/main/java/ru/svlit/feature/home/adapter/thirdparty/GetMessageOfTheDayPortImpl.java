package ru.svlit.feature.home.adapter.thirdparty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import ru.svlit.architecture.annotation.WebAdapter;
import ru.svlit.feature.home.adapter.thirdparty.model.Quote;
import ru.svlit.feature.home.adapter.thirdparty.model.QuoteApiModel;
import ru.svlit.feature.home.application.entity.MessageOfTheDay;
import ru.svlit.feature.home.application.port.out.GetMessageOfTheDayPort;
import ru.svlit.feature.home.configuration.HomeConfiguration;

@WebAdapter
@RequiredArgsConstructor
class GetMessageOfTheDayPortImpl implements GetMessageOfTheDayPort {

    private final HomeConfiguration configuration;
    private MessageOfTheDay messageOfTheDay;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public MessageOfTheDay perform() throws CouldNotFetchQuoteException {
        return new MessageOfTheDay("T", "T");
//        final MessageOfTheDay storedMessageOfTheDay = messageOfTheDay;
//        if (storedMessageOfTheDay != null) {
//            return storedMessageOfTheDay;
//        }
//
//        final MessageOfTheDay fetchedMessageOfTheDay = fetchMessageOfTheDay();
//        messageOfTheDay = fetchedMessageOfTheDay;
//
//        return fetchedMessageOfTheDay;
    }

    private MessageOfTheDay fetchMessageOfTheDay() throws CouldNotFetchQuoteException {
        final QuoteApiModel quoteApiModel = restTemplate.getForObject(
                configuration.getQuotesApiBaseUrl(),
                QuoteApiModel.class
        );

        if (quoteApiModel != null && quoteApiModel.getSuccess() != null) {
            final Quote quote = quoteApiModel.getContents().getQuotes().get(0);
            return new MessageOfTheDay(quote.getQuote(), quote.getAuthor());
        } else {
            throw new CouldNotFetchQuoteException();
        }
    }
}

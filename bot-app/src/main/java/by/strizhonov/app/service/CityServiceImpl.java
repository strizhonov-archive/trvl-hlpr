package by.strizhonov.app.service;

import by.strizhonov.app.repository.CityRepository;
import by.strizhonov.app.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Transactional
public class CityServiceImpl implements CityService {

    @Value("${cityNotFound}")
    private String cityNotFoundMessage;


    @Autowired
    private CityRepository repository;


    @Override
    public SendMessage getCityDescriptionMessage(final Update update) {
        String massageText = getResponseText(update);

        long chat_id = update.getMessage().getChatId();
        return new SendMessage()
                .setChatId(chat_id)
                .setText(massageText);
    }


    private String getResponseText(final Update update) {
        String cityToSearch = update.getMessage().getText();
        City foundCity = repository.searchByName(cityToSearch);

        return foundCity == null
                ? String.format(cityNotFoundMessage, cityToSearch)
                : foundCity.getDescription();
    }

}

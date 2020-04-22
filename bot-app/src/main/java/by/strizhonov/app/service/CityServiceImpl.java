package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CityServiceImpl implements CityService {

    @Value("${cityNotFound}")
    private String cityNotFoundMessage;


    private final FeignCityManipulationService cityManipulationService;


    @Override
    public SendMessage getCityDescriptionMessage(final Update update) {
        String messageText = getResponseText(update);

        long chatId = update.getMessage().getChatId();
        return new SendMessage()
                .setChatId(chatId)
                .setText(messageText);
    }


    private String getResponseText(final Update update) {
        String cityToSearch = update.getMessage().getText();
        CityDto foundCity = cityManipulationService.findByName(cityToSearch);

        return foundCity == null
                ? String.format(cityNotFoundMessage, cityToSearch)
                : foundCity.getDescription();
    }

}

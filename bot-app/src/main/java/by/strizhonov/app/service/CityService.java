package by.strizhonov.app.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CityService {

    SendMessage getCityDescriptionMessage(Update update);

}

package by.strizhonov.app.controller;

import by.strizhonov.app.exception.UpdateIsNotValidException;
import by.strizhonov.app.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TravelHelperBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelHelperBot.class);

    @Value("${commonError}")
    private String commonErrorMessage;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String token;

    @Autowired
    private CityService service;


    @Override
    public void onUpdateReceived(final Update update) {
        try {
            processUpdate(update);
        } catch (Exception e) {
            LOGGER.info("Unable to process [{}] update.", update, e);
        }
    }


    @Override
    public String getBotUsername() {
        return botName;
    }


    @Override
    public String getBotToken() {
        return token;
    }


    private void processUpdate(final Update updateToProcess) throws TelegramApiException, UpdateIsNotValidException {
        if (isValid(updateToProcess)) {
            processValid(updateToProcess);
        } else {
            LOGGER.info("Retrieved update [{}] is not valid.", updateToProcess);
            throw new UpdateIsNotValidException(String.format("Retrieved update [%s] is not valid.", updateToProcess));
        }
    }


    private boolean isValid(final Update updateToCheck) {
        return updateToCheck.hasMessage() && updateToCheck.getMessage().hasText();
    }


    private void processValid(final Update validUpdate) throws TelegramApiException {
        SendMessage retrievedMessage = service.getCityDescriptionMessage(validUpdate);

        try {
            this.execute(retrievedMessage);
        } catch (TelegramApiException e) {
            LOGGER.error("Unable to execute SendMessage.", e);
            sendErrorMessage(validUpdate.getMessage().getChatId(), commonErrorMessage);
        }
    }


    private void sendErrorMessage(final long chatId, final String errorText) throws TelegramApiException {
        SendMessage errorMessage = new SendMessage()
                .setChatId(chatId)
                .setText(errorText);

        this.execute(errorMessage);
    }


}

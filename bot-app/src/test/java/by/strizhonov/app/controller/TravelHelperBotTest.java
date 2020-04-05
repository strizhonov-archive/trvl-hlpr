package by.strizhonov.app.controller;

import by.strizhonov.app.service.CityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.objects.Update;

@RunWith(MockitoJUnitRunner.class)
public class TravelHelperBotTest {


    @Mock
    private CityService cityService;


    @InjectMocks
    private TravelHelperBot toTest;


    @Test
    public void shouldNotCallServiceWithInvalidUpdate() {
        Update update = new Update();
        toTest.onUpdateReceived(update);
        Mockito.verifyNoInteractions(cityService);
    }


    @Test
    public void shouldGetBotName() {
        TravelHelperBot toTest = new TravelHelperBot();
        Assert.assertNotNull(toTest.getBotUsername());
    }


    @Test
    public void shouldGetBotToken() {
        TravelHelperBot toTest = new TravelHelperBot();
        Assert.assertNotNull(toTest.getBotUsername());
    }

}

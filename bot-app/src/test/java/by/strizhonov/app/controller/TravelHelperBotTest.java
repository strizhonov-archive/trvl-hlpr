package by.strizhonov.app.controller;

import by.strizhonov.app.service.CityService;
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
    private CityService mockService;


    @InjectMocks
    private TravelHelperBot botToTest;


    @Test
    public void shouldNotCallServiceWithInvalidUpdate() {
        Update invalidUpdate = new Update();
        botToTest.onUpdateReceived(invalidUpdate);
        Mockito.verifyNoInteractions(mockService);
    }


    @Test
    public void shouldNotCallServiceWithNullUpdate() {
        botToTest.onUpdateReceived(null);
        Mockito.verifyNoInteractions(mockService);
    }

}

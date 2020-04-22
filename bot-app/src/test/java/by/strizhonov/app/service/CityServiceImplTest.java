package by.strizhonov.app.service;

import by.strizhonov.app.dto.CityDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Field;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

    @Mock
    private FeignCityManipulationService mockFeignService;


    @InjectMocks
    private CityServiceImpl serviceToTest;


    @Test
    public void shouldGetNonNullMessageToTheSameChat() throws NoSuchFieldException, IllegalAccessException {
        Mockito.when(mockFeignService.findByName(Mockito.anyString())).thenReturn(validCity());

        Update update = createTestUpdateItem(1, "TEST_NAME_1");
        SendMessage retrievedMessage = serviceToTest.getCityDescriptionMessage(update);

        Assert.assertEquals("1", retrievedMessage.getChatId());
        Assert.assertNotNull(retrievedMessage.getText());
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException() {
        serviceToTest.getCityDescriptionMessage(null);
    }


    private CityDto validCity() {
        return new CityDto(2, "TEST_NAME_2", "TEST_DESCRIPTION_2");
    }


    private Update createTestUpdateItem(final long chatId, final String existingCityName)
            throws NoSuchFieldException, IllegalAccessException {

        Chat chat = getChatWithId(chatId);
        Message message = getMessageWithChatAndText(chat, existingCityName);
        return getUpdateWithMessage(message);
    }


    private Chat getChatWithId(final long chatId) throws NoSuchFieldException, IllegalAccessException {
        Chat chat = new Chat();
        Field idField = chat.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(chat, chatId);
        return chat;
    }


    private Message getMessageWithChatAndText(final Chat chat, final String existingCityName)
            throws IllegalAccessException, NoSuchFieldException {

        Message message = new Message();
        Field chatField = message.getClass().getDeclaredField("chat");
        chatField.setAccessible(true);
        chatField.set(message, chat);

        Field textField = message.getClass().getDeclaredField("text");
        textField.setAccessible(true);
        textField.set(message, existingCityName);
        return message;
    }


    private Update getUpdateWithMessage(final Message message) throws NoSuchFieldException, IllegalAccessException {
        Update update = new Update();
        Field messageField = update.getClass().getDeclaredField("message");
        messageField.setAccessible(true);
        messageField.set(update, message);
        return update;
    }

}

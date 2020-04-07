package by.strizhonov.app.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Field;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("by.strizhonov.app")
@DataJpaTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db_inflation.sql")
public class CityServiceIT {

    @Autowired
    private CityService serviceToTest;


    @Test
    public void shouldGetCityDescriptionByCityName() throws NoSuchFieldException, IllegalAccessException {
        String input = "TEST_NAME_1";
        Update testCaseUpdate = getUpdate(input);
        SendMessage resultMessage = serviceToTest.getCityDescriptionMessage(testCaseUpdate);
        Assert.assertEquals("TEST_DESCRIPTION_1", resultMessage.getText());

        input = "TEST_NAME_2";
        testCaseUpdate = getUpdate(input);
        resultMessage = serviceToTest.getCityDescriptionMessage(testCaseUpdate);
        Assert.assertEquals("TEST_DESCRIPTION_2", resultMessage.getText());
    }


    @Test
    public void shouldGetNonNullMessageWhenNonExistingCityNamePassed()
            throws NoSuchFieldException, IllegalAccessException {

        Update testCaseUpdate = getUpdate("NON_EXISTING_NAME");
        SendMessage resultMessage = serviceToTest.getCityDescriptionMessage(testCaseUpdate);
        Assert.assertNotNull(resultMessage.getText());
    }


    private Update getUpdate(final String input) throws NoSuchFieldException, IllegalAccessException {
        Update update = new Update();
        Field messageField = update.getClass().getDeclaredField("message");
        messageField.setAccessible(true);
        messageField.set(update, getMessage(input));
        return update;
    }


    private Message getMessage(final String cityName)
            throws IllegalAccessException, NoSuchFieldException {

        Message message = new Message();
        Field chatField = message.getClass().getDeclaredField("chat");
        chatField.setAccessible(true);
        chatField.set(message, getChat());

        Field textField = message.getClass().getDeclaredField("text");
        textField.setAccessible(true);
        textField.set(message, cityName);
        return message;
    }


    private Chat getChat() throws NoSuchFieldException, IllegalAccessException {
        Chat chat = new Chat();
        Field idField = chat.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(chat, (long) 1);
        return chat;
    }

}

package by.strizhonov.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.ApiContextInitializer;


@SpringBootApplication
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
public class TravelHelperApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TravelHelperApplication.class, args);
    }

}

package by.strizhonov.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.ApiContextInitializer;

@EnableFeignClients
@SpringBootApplication
@PropertySource(value = {"classpath:messages.properties", "classpath:credentials.properties"}, encoding = "UTF-8")
public class TravelHelperApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TravelHelperApplication.class, args);
    }

}

package by.strizhonov.app.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DataSourceConfiguration {

    private static final String PARAMS_SEPARATOR = " ";

    @Value("${server.params}")
    private String serverParams;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer(serverParams.split(PARAMS_SEPARATOR));
    }

}

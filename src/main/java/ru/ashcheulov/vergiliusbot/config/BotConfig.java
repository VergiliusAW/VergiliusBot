package ru.ashcheulov.vergiliusbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Configuration
public class BotConfig {

    @Bean
    public DefaultBotOptions proxyBotOptions(@Value("#{botProperties.proxy.username}") String username,
                                             @Value("#{botProperties.proxy.password}") String password,
                                             @Value("#{botProperties.proxy.host}") String host,
                                             @Value("#{botProperties.proxy.port}") Integer port) {
        if (username == null || password == null || host == null || port == null) {
            return new DefaultBotOptions();
        }
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        });
        DefaultBotOptions botOptions = new DefaultBotOptions();

        botOptions.setProxyHost(host);
        botOptions.setProxyPort(port);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);

        return botOptions;
    }
}

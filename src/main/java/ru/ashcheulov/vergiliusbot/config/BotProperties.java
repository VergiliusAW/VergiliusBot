package ru.ashcheulov.vergiliusbot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties(prefix = "bot")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotProperties {

    @NotEmpty
    String username;

    @NotEmpty
    String token;

    ProxyProperties proxy;
}

package ru.ashcheulov.vergiliusbot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProxyProperties {
    String username;
    String password;
    String host;
    Integer port;
}

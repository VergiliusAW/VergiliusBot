package ru.ashcheulov.vergiliusbot.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ashcheulov.vergiliusbot.form.ChatState;

import java.util.List;

@Component
public class MessageHandlerRepository {

    private final List<? extends BaseMessageHandler> instances;

    @Autowired
    public MessageHandlerRepository(List<? extends BaseMessageHandler> instances) {
        this.instances = instances;
    }

    public MessageHandler getHandler(ChatState message) {
        return instances.stream().filter(h -> h.getMessage() == message).findFirst().get();
    }
}

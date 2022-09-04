package ru.ashcheulov.vergiliusbot.telegram.handlers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler {
    Logger log = LoggerFactory.getLogger(MessageHandler.class);

    public SendMessage answerMessage(Message message) {
        log.info("Vergilius bot has received message: {}", message.getText());
        String hello = "hello";
        Long chatId = message.getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(hello);
        sendMessage.setChatId(chatId);

        return sendMessage;
    }
}

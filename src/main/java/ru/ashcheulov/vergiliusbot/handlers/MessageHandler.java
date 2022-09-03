package ru.ashcheulov.vergiliusbot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.ashcheulov.vergiliusbot.form.ChatState;

public interface MessageHandler {
    SendMessage handle(String message);
}

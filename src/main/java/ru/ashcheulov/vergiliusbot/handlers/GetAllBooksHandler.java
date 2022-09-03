package ru.ashcheulov.vergiliusbot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.ashcheulov.vergiliusbot.form.ChatState;

@Component
public class GetAllBooksHandler extends BaseMessageHandler{

    GetAllBooksHandler() {
        super(ChatState.GET_ALL_BOOKS);
    }

    @Override
    public SendMessage handle(String message) {
        return null;
    }
}

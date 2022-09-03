package ru.ashcheulov.vergiliusbot.handlers;

import ru.ashcheulov.vergiliusbot.form.ChatState;

abstract class BaseMessageHandler implements MessageHandler {

    private final ChatState message;

    BaseMessageHandler(ChatState message) {
        this.message = message;
    }

    protected ChatState getMessage() {
        return message;
    }
}

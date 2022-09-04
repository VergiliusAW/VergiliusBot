package ru.ashcheulov.vergiliusbot.constants.bot;

public enum BotMessageEnum {

    HELP_MESSAGE("Привет! Я бот Вергилий"),
    EXCEPTION_ILLEGAL_MESSAGE("Нет, к такому меня не готовили!");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

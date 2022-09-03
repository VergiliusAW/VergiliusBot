package ru.ashcheulov.vergiliusbot.form;

public enum ChatState {

    ADD_NEW_BOOK("Добавить новую книгу"),

    GET_ALL_BOOKS("Получить список всех книг"),

    START("Старт");

    private final String description;

    ChatState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ChatState fromString(String string) {
        for (ChatState state : ChatState.values()) {
            if (state.getDescription().equalsIgnoreCase(string)) {
                return state;
            }
        }
        return null;
    }
}

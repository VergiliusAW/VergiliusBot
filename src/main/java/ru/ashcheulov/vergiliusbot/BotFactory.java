package ru.ashcheulov.vergiliusbot;

import ru.ashcheulov.vergiliusbot.db.BotRepository;
import ru.ashcheulov.vergiliusbot.db.BotRepositoryInMemoryDB;
import ru.ashcheulov.vergiliusbot.form.ChatsRepository;

public class BotFactory {
    public static BotRepository getBotRepository() {
        return new BotRepositoryInMemoryDB();
    }

    public static ChatsRepository getChatsRepository() {
        return new ChatsRepository();
    }
}

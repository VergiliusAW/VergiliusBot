package ru.ashcheulov.vergiliusbot;

import ru.ashcheulov.vergiliusbot.db.BotRepository;
import ru.ashcheulov.vergiliusbot.db.BotRepositoryInMemoryDB;

public class BotFactory {
    public static BotRepository getBotRepository() {
        return new BotRepositoryInMemoryDB();
    }

}

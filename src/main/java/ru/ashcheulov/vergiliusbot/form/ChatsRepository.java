package ru.ashcheulov.vergiliusbot.form;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatsRepository {

    private final Map<Long, ChatState> chatStateMap;

    public ChatsRepository() {
        chatStateMap = new HashMap<>();
    }

    public void addNewChat(long chatId) {
        chatStateMap.put(chatId, ChatState.START);
    }

    public boolean isExist(long chatId) {
        return chatStateMap.containsKey(chatId);
    }

    public ChatState getChatState(long chatId) {
        //Если чат новый, то добавляем в хранилище
        if (!isExist(chatId)) {
            addNewChat(chatId);
        }
        return chatStateMap.get(chatId);
    }

    public void updateChatState(long chatId, ChatState state) {
        chatStateMap.put(chatId, state);
    }
}

package ru.ashcheulov.vergiliusbot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ashcheulov.vergiliusbot.form.ChatState;
import ru.ashcheulov.vergiliusbot.form.ChatsRepository;

public class FormTests {

    @Test
    public void setStartForm() {
        final long chatId = 1000;

        ChatsRepository chats = BotFactory.getChatsRepository();
        chats.addNewChat(chatId);

        Assertions.assertEquals(ChatState.START, chats.getChatState(chatId));
    }

    @Test
    public void updateChatState() {
        final long chatId = 1111;
        final ChatState state = ChatState.ADD_NEW_BOOK;

        ChatsRepository chats = BotFactory.getChatsRepository();
        chats.addNewChat(chatId);
        chats.updateChatState(chatId, state);

        Assertions.assertEquals(state, chats.getChatState(chatId));
    }
}

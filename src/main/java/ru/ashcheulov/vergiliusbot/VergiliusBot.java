package ru.ashcheulov.vergiliusbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ashcheulov.vergiliusbot.db.Book;
import ru.ashcheulov.vergiliusbot.db.BotRepository;
import ru.ashcheulov.vergiliusbot.form.ChatState;
import ru.ashcheulov.vergiliusbot.form.ChatsRepository;
import ru.ashcheulov.vergiliusbot.handlers.MessageHandler;
import ru.ashcheulov.vergiliusbot.handlers.MessageHandlerRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class VergiliusBot extends TelegramLongPollingBot {

    private final String botUsername;

    private final String botToken;

    private final MessageHandlerRepository repository;

    private final ChatsRepository chats;

    private final BotRepository library;

    @Autowired
    public VergiliusBot(
            @Value("#{botProperties.username}") String botUsername,
            @Value("#{botProperties.token}") String botToken,
            MessageHandlerRepository repository,
            ChatsRepository chats,
            BotRepository library
    ) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.repository = repository;
        this.chats = chats;
        this.library = library;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            //Если сообщение является типовой командой, то ChatState меняем на соответствующий

            ChatState state = ChatState.fromString(message_text);
            if (state == null) {
                ChatState currentState = chats.getChatState(chat_id);
                MessageHandler handler = repository.getHandler(currentState);

                SendMessage message = handler.handle(message_text);
                message.setChatId(chat_id);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage message = new SendMessage();
                message.setChatId(chat_id);

                chats.updateChatState(chat_id, state);
                if (state == ChatState.ADD_NEW_BOOK) {
                    message.setText("Введите название новой книги");
                }
                if (state == ChatState.START) {
                    message.setText("Добро пожаловать!\nЯ Бот Вергилий - твой помощник в организации картотеки :)");
                    message.setReplyMarkup(getKeyboard());
                }
                if (state == ChatState.GET_ALL_BOOKS) {
                    message.setText("Выберите книгу:");
                    message.setReplyMarkup(getBooksList());
                }

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public InlineKeyboardMarkup getBooksList() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (Book book : library.getAllBooks()) {
            rows.add(getButton(book.getBookName(), book.getBookName()));
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButton(String name, String callback) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(name);
        button.setCallbackData(callback);
        return List.of(button);
    }

    public ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add(ChatState.ADD_NEW_BOOK.getDescription());
        row.add(ChatState.GET_ALL_BOOKS.getDescription());

        List<KeyboardRow> keyboard = List.of(row);
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

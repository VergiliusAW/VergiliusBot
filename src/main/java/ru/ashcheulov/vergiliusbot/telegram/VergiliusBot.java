package ru.ashcheulov.vergiliusbot.telegram;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ashcheulov.vergiliusbot.constants.bot.BotMessageEnum;
import ru.ashcheulov.vergiliusbot.telegram.handlers.CallbackQueryHandler;
import ru.ashcheulov.vergiliusbot.telegram.handlers.MessageHandler;

@Component
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VergiliusBot extends TelegramLongPollingBot {
    final Logger log = LoggerFactory.getLogger(VergiliusBot.class);

    @Value("#{botProperties.username}")
    String botUsername;
    @Value("#{botProperties.token}")
    String botToken;

    final MessageHandler messageHandler;
    final CallbackQueryHandler callbackQueryHandler;

    @Autowired
    public VergiliusBot(MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler, DefaultBotOptions botOptions) {
        super(botOptions);
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            handleRequest(update);
        } catch (IllegalArgumentException e) {
            log.info("Ошибка при обработке Update: {}", e.getMessage());
            SendMessage errorMessage = new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_ILLEGAL_MESSAGE.getMessage());
            try {
                execute(errorMessage);
            } catch (TelegramApiException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void handleRequest(Update update) {
        SendMessage sendMessage;
        if (update.hasCallbackQuery()) {
            log.info("has callback query");
//            sendMessage = new SendMessage();
            return;
        } else {
            Message message = update.getMessage();
            sendMessage = messageHandler.answerMessage(message);
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


//    public InlineKeyboardMarkup getBooksList() {
//        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
//        for (Book book : library.getAllBooks()) {
//            rows.add(getButton(book.getBookName(), book.getBookName()));
//        }
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        inlineKeyboardMarkup.setKeyboard(rows);
//        return inlineKeyboardMarkup;
//    }
//
//    private List<InlineKeyboardButton> getButton(String name, String callback) {
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        button.setText(name);
//        button.setCallbackData(callback);
//        return List.of(button);
//    }
//
//    public ReplyKeyboardMarkup getKeyboard() {
//        KeyboardRow row = new KeyboardRow();
//        row.add(ChatState.ADD_NEW_BOOK.getDescription());
//        row.add(ChatState.GET_ALL_BOOKS.getDescription());
//
//        List<KeyboardRow> keyboard = List.of(row);
//        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        replyKeyboardMarkup.setKeyboard(keyboard);
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    public ReplyKeyboardMarkup getCallbackBookReplyKeyboard() {
//        KeyboardRow row = new KeyboardRow();
//        row.add("Добавить новую запись");
//
//        KeyboardRow secondRow = new KeyboardRow();
//        secondRow.add("Прочитано");
//        secondRow.add("Получить все заметки");
//
//        List<KeyboardRow> keyboard = List.of(row, secondRow);
//        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        replyKeyboardMarkup.setKeyboard(keyboard);
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

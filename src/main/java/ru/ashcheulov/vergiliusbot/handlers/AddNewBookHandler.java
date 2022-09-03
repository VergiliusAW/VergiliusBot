package ru.ashcheulov.vergiliusbot.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.ashcheulov.vergiliusbot.db.Book;
import ru.ashcheulov.vergiliusbot.db.BotRepository;
import ru.ashcheulov.vergiliusbot.form.ChatState;

@Component
public class AddNewBookHandler extends BaseMessageHandler {
    private final static Logger log = LoggerFactory.getLogger(AddNewBookHandler.class);

    private final BotRepository repository;

    AddNewBookHandler(BotRepository repository) {
        super(ChatState.ADD_NEW_BOOK);
        this.repository = repository;
    }

    @Override
    public SendMessage handle(String message) {
        log.info("handling");
        Book book = new Book();
        book.setBookName(message);
        repository.addNewBook(book);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Книга успешно добавлена!");

        return sendMessage;
    }
}

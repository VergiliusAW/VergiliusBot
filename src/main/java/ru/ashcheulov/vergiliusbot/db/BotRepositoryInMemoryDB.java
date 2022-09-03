package ru.ashcheulov.vergiliusbot.db;

import org.springframework.stereotype.Component;
import ru.ashcheulov.vergiliusbot.exceptions.BookIsNullException;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotRepositoryInMemoryDB implements BotRepository {

    private final List<Book> books;

    public BotRepositoryInMemoryDB() {
        books = new ArrayList<>();
    }

    @Override
    public Book addNewBook(Book newBook) {
        if (newBook == null) {
            throw new BookIsNullException();
        }
        books.add(newBook);
        return newBook;
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }
}

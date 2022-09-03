package ru.ashcheulov.vergiliusbot.db;

import java.util.List;

public interface BotRepository {

    Book addNewBook(Book newBook);

    List<Book> getAllBooks();
}

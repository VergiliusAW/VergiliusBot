package ru.ashcheulov.vergiliusbot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ashcheulov.vergiliusbot.db.Book;
import ru.ashcheulov.vergiliusbot.db.BotRepository;
import ru.ashcheulov.vergiliusbot.exceptions.BookIsNullException;

import java.util.List;

public class BookTests {

    @Test
    public void createBookTest() {
        Book book = new Book();
        book.setBookName("Идеальный программист");
        book.setRead(false);

        BotRepository botRepository = BotFactory.getBotRepository();
        botRepository.addNewBook(book);

        Assertions.assertIterableEquals(List.of(book), botRepository.getAllBooks());
        Assertions.assertEquals(book.getBookName(), botRepository.getAllBooks().get(0).getBookName());
        Assertions.assertEquals(book.isRead(), botRepository.getAllBooks().get(0).isRead());
    }

    @Test
    public void addNullBookTest() {
        BotRepository botRepository = BotFactory.getBotRepository();

        Assertions.assertThrows(BookIsNullException.class, () -> botRepository.addNewBook(null));
    }
}

package ru.ashcheulov.vergiliusbot.db;

import lombok.Data;

import java.util.List;

@Data
public class Book {

    private String bookName;

    private boolean isRead;

    private List<BookNote> bookNotes;
}

package com.trilogyed.bookservice.dao;

import java.awt.print.Book;
import java.util.List;

public interface BookDao {
    Book getBook(int id);

    List<Book> getAllBooks();

    Book addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int id);
}

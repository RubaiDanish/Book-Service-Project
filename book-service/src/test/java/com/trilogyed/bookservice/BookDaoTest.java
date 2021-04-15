package com.trilogyed.bookservice;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {
    @Autowired
    protected BookDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Book> bList = dao.getAllBooks();

        bList.stream()
                .forEach(book -> dao.deleteBook(book.getId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteBook() {

        Book book = new Book();
        book.setTitle("The Cat In The Hat");
        book.setAuthor("Dr.Seuss");

        book = dao.addBook(book);

        Book book2 = dao.getBook(book.getId());

        assertEquals(book, book2);

        dao.deleteBook(book.getId());

        book2 = dao.getBook(book.getId());

        assertNull(book2);
    }

    @Test
    public void getAllBooks() {

        Book book = new Book();
        book.setTitle("The Lorax");
        book.setAuthor("Dr.Seuss");

        dao.addBook(book);

        book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("JK Rowling");

        dao.addBook(book);

        List<Book> bookList = dao.getAllBooks();

        assertEquals(bookList.size(), 2);
    }


    @Test
    public void updateBook() {

        Book book = new Book();
        book.setTitle("The Cat In The Hat");
        book.setAuthor("Dr.Seuss");

        book = dao.addBook(book);

        book.setTitle("Twilight");
        book.setAuthor("UPDATED");

        dao.updateBook(book);

        Book book2 = dao.getBook(book.getId());

        assertEquals(book2, book);
    }
}

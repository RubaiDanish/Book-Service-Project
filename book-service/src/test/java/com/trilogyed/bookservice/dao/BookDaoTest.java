package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    protected BookDao bookDao;


    @Before
    public void setUp() throws Exception {
        List<Book> mList = bookDao.getAllBooks();

        mList.stream()
                .forEach(book -> bookDao.deleteBook(book.getId()));
    }

    @Test
    public void getBook() {

    }

    @Test
    public void getAllBooks() {
    }

    @Test
    public void addBook() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void deleteBook() {
    }
}
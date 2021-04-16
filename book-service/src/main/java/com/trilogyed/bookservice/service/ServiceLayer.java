package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.feign.noteRetrieval;
import com.trilogyed.bookservice.model.Book;

public class ServiceLayer {

    private BookDao bookDao;
    private noteRetrieval noteRetrieval;

    public void createBookWithNotes(String title, String note){

        Book book1 = new Book();
        book1.setTitle(title);


    }
}

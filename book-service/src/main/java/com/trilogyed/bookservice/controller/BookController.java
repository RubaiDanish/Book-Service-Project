package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    
    @RequestMapping (value = "/books", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookDao.addBook(book);
    }

    @RequestMapping (value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Book getBook(@PathVariable int id) {
        if (id < 1) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }
        Book returnVal = bookDao.getBook(id);
        if (returnVal == null) {
            throw new IllegalArgumentException("No book with id " + id);
        }

        return returnVal;    }
    @RequestMapping (value = "books", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @RequestMapping (value = "/books/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book, @PathVariable int id) {
        if (id != book.getId()) {
            throw new IllegalArgumentException("Book ID on path must match the ID in the Book object.");
        }
    }

    @RequestMapping (value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {

    }
}

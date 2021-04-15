package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
=======

>>>>>>> 8eb45ec2f47b4520bc3135e43eeb8ac7438d1859

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcTemplateImpl implements BookDao{
    private static final String INSERT_BOOK_SQL =
            "insert into book (title, author) values (?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where id = ?";

    private static final String SELECT_ALL_BOOKS_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set title = ?, author = ? where id = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getBook(int id) {

        try {

            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }

    }

    @Override
    public List<Book> getAllBooks() {

        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }


    @Override
    @Transactional
    public Book addBook(Book book) {

        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getTitle(),
                book.getAuthor());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        book.setId(id);

        return book;
    }

    @Override
    public void updateBook(Book book) {

        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getTitle(),
                book.getAuthor(),
                book.getId());
    }

    @Override
    public void deleteBook(int id) {

        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }


    // Helper methods
    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));

        return book;
    }

}

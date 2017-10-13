package com.springapp.mvc.service;

import com.springapp.mvc.dao.BookDao;
import com.springapp.mvc.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 123 on 03.10.2017.
 */

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDao.removeBook(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByParam(String title, String description, String author, Integer page) {
        return this.bookDao.findByParam(title, description, author, page);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBooks() {
        return this.bookDao.countBooks();
    }

    @Override
    @Transactional
    public Book editStatusBook(int id) {
        Book book = this.bookDao.getBookById(id);
        if (book.isWasRead())
            book.setWasRead(false);
        bookDao.updateBook(book);
        return book;
    }
}

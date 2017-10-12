package com.springapp.mvc.service;

import com.springapp.mvc.model.Book;

import java.util.List;

/**
 * Created by 123 on 03.10.2017.
 */
public interface BookService {
    public void addBook(Book book);
    public void updateBook(Book book);
    public  void removeBook(int id);
    public Book getBookById(int id);
    public List<Book>findByParam(String title,String description,String author, Integer page);
    public Long countBooks();
}

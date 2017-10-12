package com.springapp.mvc.controller;

import com.springapp.mvc.model.Book;
import com.springapp.mvc.service.BookService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 123 on 03.10.2017.
 */

@Controller
public class BookController {
    private BookService bookService;
    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = {"/", "/books"}, method = RequestMethod.GET)
    public String listBooks(@RequestParam(required = false) Integer page, Model model) {
        model.addAttribute("book", new Book());
        Integer countPages = (int) Math.ceil((double) (this.bookService.countBooks()) / 10);
        model.addAttribute("countPages", countPages);
        if (page == null)
            page = 1;
        else if (page > countPages)
            page = countPages;
        model.addAttribute("page", page);
        List<Book> bookList = this.bookService.findByParam(null,null, null, page);
        model.addAttribute("listBooks", bookList);
        return "books";
    }

    @RequestMapping(value = "/books/add/{page}", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, @PathVariable("page") int page, Model model) {
        if (book.getId() == 0)
            this.bookService.addBook(book);
        else
            this.bookService.updateBook(book);
        return listBooks(page, model);
    }
    @RequestMapping(value = "/books/find", method = RequestMethod.POST)
    public String findBook(@RequestParam(required = false) String title,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) String author,
                           Model model) {
        Integer page = 1;
        List<Book> bookList = this.bookService.findByParam(title,description, author, null);
        model.addAttribute("listBooks", bookList);
        model.addAttribute("book", new Book());
        model.addAttribute("page", page);
        model.addAttribute("findResult", true);
        return "books";
    }
    @RequestMapping(value = "/remove/{page}/{id}")
    public String removeBook(@PathVariable("page") int page, @PathVariable("id") int id, Model model) {
        this.bookService.removeBook(id);
        return listBooks(page, model);
    }
    @RequestMapping(value = "edit/{page}/{id}")
    public String editBook(@PathVariable("page") int page, @PathVariable("id") int id, Model model) {
        Book book = this.bookService.getBookById(id);
        if (book.isWasRead())
            book.setWasRead(false);
        this.bookService.updateBook(book);
        model.addAttribute("book", book);
        model.addAttribute("listBooks", this.bookService.findByParam(null, null, null, new Integer(page)));
        return "books";
    }
    @RequestMapping(value = "edit/status/{page}/{id}")
    public String editStatusBook(@PathVariable("page") int page, @PathVariable("id") int id, Model model) {
        Book book = this.bookService.getBookById(id);
        if (!book.isWasRead())
            book.setWasRead(true);
        this.bookService.updateBook(book);
        return listBooks(page, model);
    }
    @RequestMapping(value = "bookread/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        return "bookread";
    }
}

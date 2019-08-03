package wen.multidatasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wen.multidatasource.dao.bookjpa.BookJPA;
import wen.multidatasource.domain.Book;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookJPA bookJPA;

    @RequestMapping(value = "/list")
    public List<Book> list() {
        return bookJPA.findAll();
    }
}

package org.example.book.controller;

import org.example.book.Book;
import org.example.book.Dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/json")
public class JsonController {
    @Autowired
    BookDao bookdao;

    @RequestMapping("/allbooksjson")
    public List<Book> allbookJson() {
        return (List<Book>) bookdao.findAll();
    }

    @RequestMapping("/titlebooksjson/{bookTitle}")
    public Book titlebookJson(@PathVariable("bookTitle") String title) {
        return bookdao.findByTitolo(title);
    }
}

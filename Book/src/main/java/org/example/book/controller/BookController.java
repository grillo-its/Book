package org.example.book.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.validation.Valid;
import org.example.book.Book;
import org.example.book.Dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path="/book")
public class BookController {
    @Autowired
    private BookDao bookRepository;

    @RequestMapping("/")
    public String book(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id) {
        Book book = bookRepository.findById(id); //select * from Prodotti where prodotto_id = :id

        ModelAndView modelAndView = new ModelAndView();

        if (book != null){
            modelAndView.setViewName("bookdetail");
            modelAndView.addObject("book", book);
            return modelAndView;
        }else {
            return null; //"error/404";
        }
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Book book = bookRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView();

        if (book != null){
            model.addAttribute("book", book);
            return "bookedit";
        }else {
            return "error";
        }
    }

    @GetMapping("/add")
    public String newBook(Book book){
        return "bookadd";
    }
    @PostMapping("/added")
    public String add(Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "bookadd";//se si sbaglia ci lascia dentro alla pagina html
        }
        if(bookRepository.findByTitolo(book.getTitolo())==null){
            bookRepository.save(book);
            return "redirect:/book/";
        }
        else {
            return "error";
        }

    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/book/";
    }
}

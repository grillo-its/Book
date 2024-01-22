package org.example.book.Dao;

import org.example.book.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends CrudRepository<Book, Integer>{
    Book findByTitolo(String titolo);//select * from Libro where titolo = :titolo
    List<Book> findByAutore(String autore);
    Book findById(int id);
}

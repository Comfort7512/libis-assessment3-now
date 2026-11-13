package org.example.libis.dao;

import org.example.libis.model.Book;

import java.util.List;

public interface BookDAO {

    List<Book> findAll();

    Book findById(int id);

    Book searchByTitle(String keyword);

    Book findByIsbn(String isbn);

    int create(Book book);

    int update(Book book);

    int delete(int id);
}

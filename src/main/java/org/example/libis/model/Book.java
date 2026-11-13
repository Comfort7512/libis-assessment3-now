package org.example.libis.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    private int bookId;
    private String isbn;
    private String title;
    private String author;
    private LocalDate publishedYear;
    private String genre;
    private String synopsis;

    public Book(String isbn, String title, String author, LocalDate publishedYear, String genre, String synopsis) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.synopsis = synopsis;
    }


}

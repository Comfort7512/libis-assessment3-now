package org.example.libis.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    private int bookId;
    private String isbn;
    private String title;
    private String author;
    private String publishedYear;
    private String genre;
    private String synopsis;


}

package com.example.demo;

import com.example.demo.Authors.Author;
import com.example.demo.Authors.AuthorService;
import com.example.demo.Books.Book;
import com.example.demo.Books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public DataInitializer(AuthorService authorService, BookService bookService){
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void init(){
        List<Author> authors = Arrays.asList(
                new Author(1L),
                new Author(2L),
                new Author(3L)
        );

        authors.forEach(author -> authorService.add(author));

        List<Book> books = Arrays.asList(
                new Book("Zbrodnia i Kara",550, authors.get(0)),
                new Book("Pan Tadeuse", 440, authors.get(2)),
                new Book("Przemiana", 150, authors.get(1))
        );

        authorService.find(1L).get().getBooks().add(books.get(0));
        authorService.find(2L).get().getBooks().add(books.get(2));
        authorService.find(3L).get().getBooks().add(books.get(1));

        books.forEach(book -> bookService.add(book));
    }
}

package com.example.demo;

import com.example.demo.Authors.Author;
import com.example.demo.Authors.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private AuthorService authorService;

    @Autowired
    public DataInitializer(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostConstruct
    public void init(){
        List<Author> authors = Arrays.asList(
                new Author("Fiodor","Dostojewski",false),
                new Author("Franz", "Kafka",false),
                new Author("Adam", "Mickiewicz", false)
        );

        authors.forEach(author -> authorService.create(author));
    }
}

package com.example.demo.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void add(Book book){
        bookRepository.save(book);
    }
    public void delete(Long id){
        bookRepository.deleteById(id);
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Optional<Book> find(Long id){
        return bookRepository.findById(id);
    }
}

package com.example.demo.Authors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService (AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public void add(Author author){
        authorRepository.save(author);
    }
    public void delete(Long id){
        authorRepository.deleteById(id);
    }
    public List<Author> findAll(){
        return authorRepository.findAll();
    }
    public Optional<Author> find(Long id){
        return authorRepository.findById(id);
    }
}

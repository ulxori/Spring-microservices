package com.example.demo.Authors;

import com.example.demo.Authors.Event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private EventRepository eventRepository;

    @Autowired
    public AuthorService (AuthorRepository authorRepository, EventRepository eventRepository){
        this.authorRepository = authorRepository;
        this.eventRepository = eventRepository;
    }

    public void add(Author author){
        Author tmpAuthor = authorRepository.save(author);
        eventRepository.create(tmpAuthor);
    }
    public void create(Author author){
        authorRepository.save(author);
    }
    public void delete(Long id){
        eventRepository.delete(id);
        authorRepository.deleteById(id);
    }
    public List<Author> findAll(){
        return authorRepository.findAll();
    }
    public Optional<Author> find(Long id){
        return authorRepository.findById(id);
    }
}

package com.example.demo.Authors.Controller;

import com.example.demo.Authors.Author;
import com.example.demo.Authors.AuthorService;
import com.example.demo.Authors.Dto.AuthorDtoReadAddUpdate;
import com.example.demo.Authors.Dto.AuthorToDtoMapper;
import com.example.demo.Authors.Dto.AuthorDtoRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/1/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDtoRead>> getAuthors(){
        List<Author> all = authorService.findAll();
        List<AuthorDtoRead> authors = all.stream()
                .map(author -> new AuthorToDtoMapper().mapToDtoAuthorRead(author))
                .collect(Collectors.toList());

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDtoReadAddUpdate> getAuthor(@PathVariable("id") Long id){
        Optional<Author> selectedAuthor = authorService.find(id);
        if(selectedAuthor.isPresent()){
            return new ResponseEntity<>(new AuthorToDtoMapper().mapToDtoAuthorReadAddUpdate(selectedAuthor.get()), HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<AuthorDtoReadAddUpdate> addAuthor(@RequestBody Author author){
        authorService.add(author);
        return  new ResponseEntity<>(new AuthorToDtoMapper().mapToDtoAuthorReadAddUpdate(author),HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id){
        Optional<Author> selectedAuthor = authorService.find(id);
        if(selectedAuthor.isPresent()){
            authorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorDtoReadAddUpdate> updateAuthor(@PathVariable("id") Long id, @RequestBody Author author){
        Optional<Author> selectedAuthor = authorService.find(id);
        if(selectedAuthor.isPresent()){
            selectedAuthor.get().setAlive(author.isAlive());
            selectedAuthor.get().setName(author.getName());
            selectedAuthor.get().setSurname(author.getSurname());
            authorService.add(selectedAuthor.get());
            return new ResponseEntity<>(new AuthorToDtoMapper().mapToDtoAuthorReadAddUpdate(selectedAuthor.get()),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package com.example.demo.Authors.Controller;

import com.example.demo.Authors.Author;
import com.example.demo.Authors.AuthorService;
import com.example.demo.Authors.Dto.AuthorDto;
import com.example.demo.Authors.Dto.AuthorToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/1/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping()
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody Author author){
        System.out.println(author);
        authorService.add(author);
        return  new ResponseEntity<>(new AuthorToDtoMapper().mapToDto(author),HttpStatus.CREATED);
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
}

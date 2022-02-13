package com.example.demo.Authors.Dto;

import com.example.demo.Authors.Author;

public class AuthorToDtoMapper {

    public AuthorDtoReadAddUpdate mapToDtoAuthorReadAddUpdate(Author author){
        AuthorDtoReadAddUpdate newAuthor = new AuthorDtoReadAddUpdate();
        newAuthor.setAlive(author.isAlive());
        newAuthor.setName(author.getName());
        newAuthor.setSurname(author.getSurname());
        return newAuthor;
    }

    public AuthorDtoRead mapToDtoAuthorRead(Author author){
        AuthorDtoRead newAuthor = new AuthorDtoRead();
        newAuthor.setName(author.getName());
        newAuthor.setSurname(author.getSurname());
        newAuthor.setId(author.getId());
        return newAuthor;
    }
}

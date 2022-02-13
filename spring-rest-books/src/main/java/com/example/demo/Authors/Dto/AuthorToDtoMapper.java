package com.example.demo.Authors.Dto;

import com.example.demo.Authors.Author;

public class AuthorToDtoMapper {

    public AuthorDto mapToDto(Author author){
        AuthorDto newAuthor = new AuthorDto();
        newAuthor.setId(author.getId());
        return newAuthor;
    }
}

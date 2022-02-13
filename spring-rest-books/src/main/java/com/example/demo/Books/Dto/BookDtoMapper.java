package com.example.demo.Books.Dto;

import com.example.demo.Books.Book;

public class BookDtoMapper {

    public BookDtoAddUpdate mapToBookDtoAddUpdate(Book book){

        BookDtoAddUpdate newBook = new BookDtoAddUpdate();
        newBook.setNumberOfPages(book.getNumberOfPages());
        newBook.setTitle(book.getTitle());

        return newBook;
    }

    public BookDtoRead mapToBookDtoRead(Book book){

        BookDtoRead newBook = new BookDtoRead();
        newBook.setId(book.getId());
        newBook.setTitle(book.getTitle());

        return newBook;
    }
}

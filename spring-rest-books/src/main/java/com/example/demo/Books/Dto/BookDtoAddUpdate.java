package com.example.demo.Books.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookDtoAddUpdate {

    private String  title;
    private int numberOfPages;
}

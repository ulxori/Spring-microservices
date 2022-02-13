package com.example.demo.Authors.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDtoRead {

    private Long id;
    private String name;
    private String surname;
}

package com.example.demo.Authors.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AuthorDtoReadAddUpdate {

    String name;
    String surname;
    boolean isAlive;
}




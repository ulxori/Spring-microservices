package com.example.demo.Authors;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "author_name")
    private String name;

    private String surname;

    private boolean isAlive;

    public Author(String name, String surname, boolean isAlive){
        this.name = name;
        this.surname = surname;
        this.isAlive = isAlive;
    }
}

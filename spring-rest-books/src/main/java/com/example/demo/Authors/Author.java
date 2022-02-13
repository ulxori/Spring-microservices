package com.example.demo.Authors;

import com.example.demo.Books.Book;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "authors")
public class Author {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    //@SequenceGenerator(name="author_generator", sequenceName = "author_seq", allocationSize=1)
    private Long id;

    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Book> books;

    public Author(Long id){
        this.id = id;
        this.books = new ArrayList<>();
    }
    public Author(){
        this.books = new ArrayList<>();
    }
}

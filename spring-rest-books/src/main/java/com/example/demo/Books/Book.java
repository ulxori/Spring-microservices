package com.example.demo.Books;

import com.example.demo.Authors.Author;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name="book_generator", sequenceName = "book_seq", allocationSize=1)
    private Long id;

    private String title;

    private int numberOfPages;

    @ManyToOne
    @JoinColumn(name="author")
    private Author author;

    public Book(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public Book(String title, int numberOfPages, Author author) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
    }
}

package com.example.demo.Books.Contoller;

import com.example.demo.Authors.Author;
import com.example.demo.Authors.AuthorService;
import com.example.demo.Books.Book;
import com.example.demo.Books.BookService;
import com.example.demo.Books.Dto.BookDtoAddUpdate;
import com.example.demo.Books.Dto.BookDtoMapper;
import com.example.demo.Books.Dto.BookDtoRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/1/authors/{authorid}/books")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService){
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<BookDtoRead>> getBooks(@PathVariable("authorid") Long authorid){
        Optional<Author> selectedAuthor = authorService.find(authorid);
        if(selectedAuthor.isPresent()){
            List<Book> all = bookService.findAll().stream()
                    .filter(book -> book.getAuthor().getId().equals(authorid))
                    .collect(Collectors.toList());

            List<BookDtoRead> books = all.stream()
                    .map(book -> new BookDtoMapper().mapToBookDtoRead(book))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(books, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDtoAddUpdate> getBook(@PathVariable("authorid") Long authorId,
                                               @PathVariable("id") Long id){
        Optional<Author> selectedAuthor = authorService.find(authorId);
        if(selectedAuthor.isPresent()){
            Optional<Book> selectedBook = bookService.find(id);

            if(selectedBook.isPresent()){
                if(selectedBook.get().getAuthor().getId().equals(authorId)){
                    return new ResponseEntity<>(new BookDtoMapper().mapToBookDtoAddUpdate(selectedBook.get()), HttpStatus.OK);
                }
            }
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<BookDtoAddUpdate> addBook(@PathVariable("authorid") Long authorId,
                                                    @RequestBody Book book){
        Optional<Author> selectedAuthor = authorService.find(authorId);

        if(selectedAuthor.isPresent()){
            book.setAuthor(selectedAuthor.get());
            bookService.add(book);
            selectedAuthor.get().getBooks().add(book);
            return new ResponseEntity<>(new BookDtoMapper().mapToBookDtoAddUpdate(book), HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("authorid") Long authorId,
                                           @PathVariable("id") Long id){
        Optional<Author> selectedAuthor = authorService.find(authorId);
        if(selectedAuthor.isPresent()){
            Optional<Book> selectedBook = bookService.find(id);
            if(selectedBook.isPresent()){
                if(selectedBook.get().getAuthor().getId().equals(authorId)){
                    bookService.delete(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDtoAddUpdate> updateBook(@PathVariable("authorid") Long authorId,
                                                       @PathVariable("id") Long id, @RequestBody Book book){
        Optional<Author> selectedAuthor = authorService.find(authorId);

        if(selectedAuthor.isPresent()){
            Optional<Book> selectedBook = bookService.find(id);
            if(selectedBook.isPresent()){
                if(selectedBook.get().getAuthor().getId().equals(authorId)){
                    selectedBook.get().setTitle(book.getTitle());
                    selectedBook.get().setNumberOfPages(book.getNumberOfPages());
                    bookService.add(selectedBook.get());
                    return new ResponseEntity<>(new BookDtoMapper().mapToBookDtoAddUpdate(selectedBook.get()),
                            HttpStatus.ACCEPTED);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

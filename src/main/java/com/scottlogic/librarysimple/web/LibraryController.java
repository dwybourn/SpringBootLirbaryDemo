package com.scottlogic.librarysimple.web;

import com.scottlogic.librarysimple.domain.Book;
import com.scottlogic.librarysimple.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
public class LibraryController {

    private final BookService bookService;

    public LibraryController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books/{name}")
    @ResponseBody
    public Book getBook(@PathVariable("name") final String name){
        final Optional<Book> book = bookService.getByName(name);
        if(book.isPresent()){
            return book.get();
        } else {
            throw new EntityNotFoundException("No book found with name " + name);
        }
    }

    @PostMapping(value = "/books")
    public void addBook(@RequestBody final Book book){
        bookService.add(book);
    }
}

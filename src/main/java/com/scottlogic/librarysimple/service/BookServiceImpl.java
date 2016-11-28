package com.scottlogic.librarysimple.service;

import com.scottlogic.librarysimple.dao.BookRepository;
import com.scottlogic.librarysimple.domain.Book;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> getByName(final String name) {
        return bookRepository.findByNameIgnoringCase(name);
    }

    @Override
    public void add(final Book book) {
        bookRepository.save(book);
    }
}

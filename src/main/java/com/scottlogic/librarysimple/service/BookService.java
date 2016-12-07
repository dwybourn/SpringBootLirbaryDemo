package com.scottlogic.librarysimple.service;


import com.scottlogic.librarysimple.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getByName(final String name);

    void add(final Book book);
}

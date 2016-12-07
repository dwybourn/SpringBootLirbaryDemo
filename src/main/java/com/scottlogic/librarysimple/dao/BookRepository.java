package com.scottlogic.librarysimple.dao;

import com.scottlogic.librarysimple.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByNameIgnoringCase(final String name);
}

package com.scottlogic.librarysimple.dao;

import com.scottlogic.librarysimple.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldGetBookByNameIgnoringCase(){
        entityManager.persist(new Book("Java"));

        final Optional<Book> result = bookRepository.findByNameIgnoringCase("java");

        assertThat(result.get().getName(), equalTo("Java"));
    }

    @Test
    public void shouldAddBook(){
        final Book book = new Book("Java");

        bookRepository.save(book);

        final Book result = entityManager.find(Book.class, book.getBookId());
        assertThat(result, equalTo(book));
    }
}

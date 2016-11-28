package com.scottlogic.librarysimple.service;

import com.scottlogic.librarysimple.dao.BookRepository;
import com.scottlogic.librarysimple.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @Before
    public void setUp(){
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void shouldGetBookByName(){
        when(bookRepository.findByNameIgnoringCase("Java")).thenReturn(Optional.of(new Book("Java")));

        final Optional<Book> result = bookService.getByName("Java");

        assertThat(result.get().getName(), equalTo("Java"));
    }

    @Test
    public void shouldAddBook(){
        final Book book = new Book("Java");

        bookService.add(book);

        verify(bookRepository).save(book);
    }

}

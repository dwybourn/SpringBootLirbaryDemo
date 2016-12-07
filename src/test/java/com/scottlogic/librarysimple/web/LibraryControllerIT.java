package com.scottlogic.librarysimple.web;

import com.scottlogic.librarysimple.dao.BookRepository;
import com.scottlogic.librarysimple.domain.Book;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LibraryControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @After
    public void tearDown(){
        bookRepository.deleteAll();
    }

    @Test
    public void shouldRetrieveBook(){
        final Book book = new Book("Java");
        bookRepository.save(book);

        final ResponseEntity<Book> result = restTemplate.getForEntity("/books/{name}", Book.class, "Java");

        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(result.getBody(), equalTo(book));
    }

    @Test
    public void shouldAddBook(){
        final Book book = new Book("Java");

        final ResponseEntity<Void> response = restTemplate.postForEntity("/books", book, Void.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        final Optional<Book> addedBook = bookRepository.findByNameIgnoringCase("java");
        assertThat(addedBook.get(), equalTo(book));
    }

    @Test
    public void shouldReturnNotFoundWhenBookDoesNotExist(){
        final ResponseEntity<String> result = restTemplate.getForEntity("/books/{name}", String.class, "Java");

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result.getBody(), equalTo("No book found with name Java"));
    }
}

package com.scottlogic.librarysimple.domain;

import javax.persistence.*;

@Entity
public class Book {

    private Long bookId;

    private String name;

    public Book() {
        //empty constructor required by hibernate
    }

    public Book(final String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(final Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name){
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Book book = (Book) o;

        return name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}



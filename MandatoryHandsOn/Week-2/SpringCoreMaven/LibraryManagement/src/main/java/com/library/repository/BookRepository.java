package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public void findAll() {
        System.out.println("BookRepository: Fetching all books from database");
    }

    public void save(String bookTitle) {
        System.out.println("BookRepository: Saving book - " + bookTitle);
    }

    public void delete(int bookId) {
        System.out.println("BookRepository: Deleting book with id - " + bookId);
    }
}

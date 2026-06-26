package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    // Constructor injection (Exercise 7)
    public BookService() {
    }

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Setter injection (Exercise 2 & 7)
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void getAllBooks() {
        System.out.println("BookService: Getting all books");
        bookRepository.findAll();
    }

    public void addBook(String title) {
        System.out.println("BookService: Adding book - " + title);
        bookRepository.save(title);
    }

    public void removeBook(int id) {
        System.out.println("BookService: Removing book with id - " + id);
        bookRepository.delete(id);
    }
}

package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService() {}

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title) {
        bookRepository.save(title);
    }

    public List<String> getAllBooks() {
        return bookRepository.findAll();
    }

    public String findBook(String title) {
        return bookRepository.findByTitle(title);
    }
}

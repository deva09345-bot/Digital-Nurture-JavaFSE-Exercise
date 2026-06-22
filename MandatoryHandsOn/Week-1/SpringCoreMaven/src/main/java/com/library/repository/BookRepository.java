package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private List<String> books = new ArrayList<>();

    public void save(String title) {
        books.add(title);
        System.out.println("BookRepository: Saved book - " + title);
    }

    public List<String> findAll() {
        return books;
    }

    public String findByTitle(String title) {
        for (String book : books) {
            if (book.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

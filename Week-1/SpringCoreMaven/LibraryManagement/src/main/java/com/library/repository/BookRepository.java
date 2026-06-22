package com.library.repository;

import com.library.model.Book;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> books = new ArrayList<>();

    public void save(Book book) {
        books.add(book);
        System.out.println("Repository: Saved " + book);
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }
}

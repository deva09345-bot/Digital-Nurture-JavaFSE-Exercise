package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.addBook(new Book(1, "Clean Code",     "Robert C. Martin"));
        bookService.addBook(new Book(2, "Effective Java", "Joshua Bloch"));

        System.out.println("\nAll Books:");
        bookService.getAllBooks().forEach(System.out::println);

        System.out.println("\nFind by ID=1: " + bookService.getBookById(1));

        ((ClassPathXmlApplicationContext) context).close();
    }
}

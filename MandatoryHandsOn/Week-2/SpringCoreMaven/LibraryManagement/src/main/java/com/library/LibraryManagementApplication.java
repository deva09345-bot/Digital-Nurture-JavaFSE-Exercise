package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {

    public static void main(String[] args) {
        // Load Spring context from applicationContext.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get BookService bean from context
        BookService bookService = context.getBean("bookService", BookService.class);

        // Test Exercise 1 & 2: Basic Spring Application + Dependency Injection
        System.out.println("=== Testing Basic Spring Application ===");
        bookService.getAllBooks();

        // Test Exercise 3 & 8: AOP Logging
        System.out.println("\n=== Testing AOP Logging ===");
        bookService.addBook("Spring in Action");
        bookService.removeBook(1);

        ((ClassPathXmlApplicationContext) context).close();
    }
}

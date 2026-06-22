package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class LibraryManagementApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("=== Exercise 1,2,5: Spring IoC + Dependency Injection ===");
        bookService.addBook("Clean Code");
        bookService.addBook("Effective Java");
        bookService.addBook("Design Patterns");

        System.out.println("\n=== All Books ===");
        List<String> books = bookService.getAllBooks();
        for (String book : books) {
            System.out.println("  - " + book);
        }

        System.out.println("\n=== Find Book ===");
        String found = bookService.findBook("Effective Java");
        System.out.println("Found: " + found);

        System.out.println("\n=== Exercise 3: AOP Logging (see logs above each method) ===");
        bookService.getAllBooks();

        ((ClassPathXmlApplicationContext) context).close();
    }
}

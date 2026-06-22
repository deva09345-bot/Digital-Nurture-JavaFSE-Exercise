package com.di;

// Dependency injected via constructor (Constructor Injection)
public class MyApplication {
    private MessageService service;

    public MyApplication(MessageService service) {
        this.service = service;
    }

    public void processMessage(String message, String receiver) {
        service.sendMessage(message, receiver);
    }
}

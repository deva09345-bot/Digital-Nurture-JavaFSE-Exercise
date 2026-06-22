package com.di;
// Exercise 5: Dependency Injection Pattern
public class DITest {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        MyApplication app1 = new MyApplication(emailService);
        app1.processMessage("Hello via Email!", "alice@example.com");

        MessageService smsService = new SMSService();
        MyApplication app2 = new MyApplication(smsService);
        app2.processMessage("Hello via SMS!", "+1234567890");
    }
}

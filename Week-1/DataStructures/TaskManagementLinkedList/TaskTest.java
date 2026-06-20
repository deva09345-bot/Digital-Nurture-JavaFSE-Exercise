package com.task;
// Exercise 7: Task Management using Linked List
public class TaskTest {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        list.addTask(new Task(1, "Design UI",    "Pending"));
        list.addTask(new Task(2, "Develop API",  "In Progress"));
        list.addTask(new Task(3, "Write Tests",  "Pending"));

        System.out.println("\nSearch Task 2: " + list.searchById(2));
        list.deleteTask(2);

        System.out.println("\nAll Tasks:");
        list.traverse();
    }
}

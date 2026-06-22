package com.task;

public class TaskLinkedList {
    private Task head;

    // Add to end O(n)
    public void addTask(Task t) {
        if (head == null) { head = t; return; }
        Task cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = t;
        System.out.println("Added: " + t);
    }

    // Search O(n)
    public Task searchById(int id) {
        Task cur = head;
        while (cur != null) {
            if (cur.taskId == id) return cur;
            cur = cur.next;
        }
        return null;
    }

    // Traverse O(n)
    public void traverse() {
        Task cur = head;
        while (cur != null) { System.out.println(cur); cur = cur.next; }
    }

    // Delete O(n)
    public void deleteTask(int id) {
        if (head == null) return;
        if (head.taskId == id) { System.out.println("Deleted: " + head); head = head.next; return; }
        Task cur = head;
        while (cur.next != null) {
            if (cur.next.taskId == id) {
                System.out.println("Deleted: " + cur.next);
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
        System.out.println("Task ID " + id + " not found.");
    }
}

package com.employee;

public class EmployeeManager {
    private Employee[] employees;
    private int size = 0;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
    }

    // Add O(1)
    public void addEmployee(Employee e) {
        if (size < employees.length) {
            employees[size++] = e;
            System.out.println("Added: " + e);
        } else {
            System.out.println("Array full.");
        }
    }

    // Search O(n)
    public Employee searchById(int id) {
        for (int i = 0; i < size; i++)
            if (employees[i].getEmployeeId() == id) return employees[i];
        return null;
    }

    // Traverse O(n)
    public void traverseAll() {
        for (int i = 0; i < size; i++) System.out.println(employees[i]);
    }

    // Delete O(n) - shift elements
    public void deleteEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) {
                System.out.println("Deleted: " + employees[i]);
                for (int j = i; j < size - 1; j++) employees[j] = employees[j+1];
                employees[--size] = null;
                return;
            }
        }
        System.out.println("Employee ID " + id + " not found.");
    }
}

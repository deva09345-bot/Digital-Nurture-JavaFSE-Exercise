package com.employee;
// Exercise 2: Employee Management System using Arrays and Search Operations
public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeManager mgr = new EmployeeManager(10);

        mgr.addEmployee(new Employee(1, "Alice", "Manager",  85000));
        mgr.addEmployee(new Employee(2, "Bob",   "Developer",72000));
        mgr.addEmployee(new Employee(3, "Carol", "Designer", 65000));

        System.out.println("\nSearch ID=2: " + mgr.searchById(2));
        mgr.deleteEmployee(2);

        System.out.println("\nAll Employees:");
        mgr.traverseAll();
    }
}

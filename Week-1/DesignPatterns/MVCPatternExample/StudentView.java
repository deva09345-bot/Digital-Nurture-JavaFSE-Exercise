package com.mvc;

public class StudentView {
    public void displayStudentDetails(String name, int rollNo, String grade) {
        System.out.println("Student Details:");
        System.out.println("  Name   : " + name);
        System.out.println("  RollNo : " + rollNo);
        System.out.println("  Grade  : " + grade);
    }
}

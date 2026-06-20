package com.mvc;
// Exercise 7: MVC Pattern
public class MVCTest {
    public static void main(String[] args) {
        Student model      = new Student("Alice", 101, "A");
        StudentView view   = new StudentView();
        StudentController controller = new StudentController(model, view);

        controller.updateView();

        controller.setStudentName("Alice Johnson");
        controller.setStudentGrade("A+");
        controller.updateView();
    }
}

package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static EmployeeService employeeService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        employeeService = context.getBean(EmployeeService.class);
        LOGGER.info("Inside main");

        testGetEmployeesAboveSalary();
        testGetPermanentEmployees();
        testGetEmployeesByCriteria();
    }

    private static void testGetEmployeesAboveSalary() {
        LOGGER.info("Start");
        List<Employee> list = employeeService.getEmployeesAboveSalary(60000);
        LOGGER.debug("Employees above 60000:{}", list);
        LOGGER.info("End");
    }

    private static void testGetPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> list = employeeService.getPermanentEmployees();
        LOGGER.debug("Permanent employees:{}", list);
        LOGGER.info("End");
    }

    private static void testGetEmployeesByCriteria() {
        LOGGER.info("Start");
        // Dynamic criteria: salary >= 55000, permanent = true, name contains 'a'
        List<Employee> list = employeeService.getEmployeesByCriteria(55000.0, true, "a");
        LOGGER.debug("Criteria employees:{}", list);
        LOGGER.info("End");
    }
}

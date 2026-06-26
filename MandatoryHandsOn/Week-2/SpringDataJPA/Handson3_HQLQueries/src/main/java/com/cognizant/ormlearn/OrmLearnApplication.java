package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.AttemptOption;
import com.cognizant.ormlearn.model.AttemptQuestion;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.AttemptService;
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
    private static AttemptService attemptService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        employeeService = context.getBean(EmployeeService.class);
        attemptService  = context.getBean(AttemptService.class);
        LOGGER.info("Inside main");

        // HQL2 - permanent employees with fetch
        testGetAllPermanentEmployees();

        // HQL3 - quiz attempt detail
        testGetAttemptDetail();

        // HQL4 - average salary
        testGetAverageSalary();
        testGetAverageSalaryByDepartment();

        // HQL5 - native query
        testGetAllEmployeesNative();
    }

    // HQL2
    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }

    // HQL3
    private static void testGetAttemptDetail() {
        LOGGER.info("Start");
        Attempt attempt = attemptService.getAttempt(1, 1);
        LOGGER.debug("Attempt:{}", attempt);
        for (AttemptQuestion aq : attempt.getAttemptQuestionList()) {
            System.out.println(aq.getQuestion().getText());
            int i = 1;
            for (AttemptOption ao : aq.getAttemptOptionList()) {
                System.out.printf(" %d) %-15s %.1f     %b%n",
                        i++,
                        ao.getOption().getText(),
                        aq.getQuestion().getScore(),
                        ao.isSelected());
            }
        }
        LOGGER.info("End");
    }

    // HQL4
    private static void testGetAverageSalary() {
        LOGGER.info("Start");
        double avg = employeeService.getAverageSalary();
        LOGGER.debug("Average Salary:{}", avg);
        LOGGER.info("End");
    }

    private static void testGetAverageSalaryByDepartment() {
        LOGGER.info("Start");
        double avg = employeeService.getAverageSalaryByDepartment(2);
        LOGGER.debug("Average Salary for dept 2:{}", avg);
        LOGGER.info("End");
    }

    // HQL5
    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("All Employees (Native):{}", employees);
        LOGGER.info("End");
    }
}

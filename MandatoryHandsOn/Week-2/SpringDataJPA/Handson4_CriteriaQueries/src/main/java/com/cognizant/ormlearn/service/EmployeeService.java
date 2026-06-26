package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Employee_;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    // Handson4 - Criteria Query: find employees with salary > given amount
    @Transactional
    public List<Employee> getEmployeesAboveSalary(double salary) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        Predicate salaryPredicate = cb.greaterThan(root.get("salary"), salary);
        cq.where(salaryPredicate);

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    // Handson4 - Criteria Query: find permanent employees
    @Transactional
    public List<Employee> getPermanentEmployees() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        Predicate permanentPredicate = cb.equal(root.get("permanent"), true);
        cq.where(permanentPredicate);

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    // Handson4 - Criteria Query: dynamic filter — salary + permanent (simulates Amazon-like filter)
    @Transactional
    public List<Employee> getEmployeesByCriteria(Double minSalary, Boolean permanent, String nameKeyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        if (minSalary != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), minSalary));
        }
        if (permanent != null) {
            predicates.add(cb.equal(root.get("permanent"), permanent));
        }
        if (nameKeyword != null && !nameKeyword.isEmpty()) {
            predicates.add(cb.like(root.get("name"), "%" + nameKeyword + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}

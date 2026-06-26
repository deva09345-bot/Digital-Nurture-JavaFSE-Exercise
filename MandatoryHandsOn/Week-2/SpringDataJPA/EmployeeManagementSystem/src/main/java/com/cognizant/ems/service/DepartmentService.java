package com.cognizant.ems.service;

import com.cognizant.ems.model.Department;
import com.cognizant.ems.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    public Department getDepartmentById(int id) {
        Optional<Department> dept = departmentRepository.findById(id);
        if (!dept.isPresent()) throw new RuntimeException("Department not found: " + id);
        return dept.get();
    }

    @Transactional
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}

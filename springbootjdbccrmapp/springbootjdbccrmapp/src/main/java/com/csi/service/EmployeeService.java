package com.csi.service;

import com.csi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void signUp(Employee employee);

    boolean signIn(String empEmailId, String empPassword);

    void saveAll(List<Employee> employeeList);

    Employee findById(int empId);

    List<Employee> findAll();

    void update(int empId, Employee employee);

    void deleteById(int empId);

    void deleteAll();
}

package com.csi.controller;


import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeServiceImpl;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("SignUp Done Successfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @PostMapping("/saveall")
    public ResponseEntity<String> saveAll(@RequestBody List<Employee> employeeList) {
        employeeServiceImpl.saveAll(employeeList);
        return ResponseEntity.ok("All Data Saved Successfully");
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Employee> findById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @GetMapping("/findbyname/{empName}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp -> emp.getEmpName().equals(empName)).toList());
    }

    @GetMapping("/findbycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> findByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp -> emp.getEmpContactNumber() == empContactNumber).toList().get(0));
    }

    @GetMapping("/findbyemail/{empEmailId}")
    public ResponseEntity<Employee> findByEmail(@PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp -> emp.getEmpEmailId().equals(empEmailId)).toList().get(0));
    }

    @GetMapping("/findbydob/{empDOB}")
    public ResponseEntity<List<Employee>> findByDOB(@PathVariable String empDOB) {

        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp -> dateFormat.format(emp.getEmpDOB()).equals(empDOB)).toList());
    }

    @GetMapping("/anyinput/{input}")
    public ResponseEntity<List<Employee>> findByAnyInput(@PathVariable String input) {


        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp -> dateFormat.format(emp.getEmpDOB()).equals(input)
                || emp.getEmpName().equals(input)
                || String.valueOf(emp.getEmpId()).equals(input)
                || String.valueOf(emp.getEmpContactNumber()).equals(input)
                || emp.getEmpEmailId().equals(input)
                || emp.getEmpAddress().equals(input)
                || String.valueOf(emp.getEmpSalary()).equals(input)).toList());
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortById() {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).toList());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName() {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList());
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortbySalary() {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).toList());
    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.findAll().stream().filter(emp -> emp.getEmpSalary() >= empSalary).toList());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<String> update(@PathVariable int empId, @RequestBody Employee employee) {

        employeeServiceImpl.update(empId, employee);
        return ResponseEntity.ok("Data Updated Successfully");
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId) {
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        employeeServiceImpl.deleteAll();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }


}

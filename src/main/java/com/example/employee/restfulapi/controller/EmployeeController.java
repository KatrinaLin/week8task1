package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    //在此处完成Employee API

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployeeWithId(@PathVariable(value = "id") long id) {
        return employeeRepository.findOne(id);
    }


}

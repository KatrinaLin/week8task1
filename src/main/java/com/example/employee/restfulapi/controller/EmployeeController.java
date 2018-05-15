package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Employee> getEmployeesInPage(@PathVariable(value = "page") int page, @PathVariable(value = "pageSize") int pageSize) {
        if (page < 1) return null;
        return employeeRepository.findAll(new PageRequest(page - 1, pageSize));
    }

    @RequestMapping(value = "/male", method = RequestMethod.GET)
    public List<Employee> getEmployeesMale() {
        return employeeRepository.findByGenderEquals("male");
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Employee> createEmployee() {
        Employee employee = new Employee();

        employeeRepository.save(employee);

        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public List<Employee> updateEmployeeWithId(@PathVariable(value = "id") long id) {
        Employee employee = employeeRepository.findOne(id);

        // update employee

        employeeRepository.save(employee);

        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<Employee> deleteEmployeeWithId(@PathVariable(value = "id") long id) {
        employeeRepository.delete(id);

        return employeeRepository.findAll();
    }

}

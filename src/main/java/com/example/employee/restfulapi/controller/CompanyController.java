package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    //在此处完成Company API
    @RequestMapping(method = GET)
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Company getCompanyWithId(@PathVariable(value="id") Long companyId) {
        return companyRepository.findOne(companyId);
    }

    @RequestMapping(value = "/{id}/employees", method = GET)
    public List<Employee> getEmployeesForCompanyWithId(@PathVariable(value="id") Long companyId) {
        return companyRepository.findOne(companyId).getEmployees();
    }

    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}", method = GET)
    public Page<Company> getCompanyInPages(@PathVariable(value="page") int page, @PathVariable(value="pageSize") int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize);
        return companyRepository.findAll(pageable);
    }

    @RequestMapping(method = POST)
    public void addCompany() {
        Company company = new Company();
        companyRepository.save(company);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public void updateCompanyWithId(@PathVariable(value = "id") long id) {
        Company company = companyRepository.findOne(id);

        // update the company

        companyRepository.save(company);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteCompanyWithId(@PathVariable(value = "id") long id) {
        companyRepository.delete(id);
    }
}

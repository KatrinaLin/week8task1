package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    //在此处完成Company API
    @RequestMapping(method = RequestMethod.GET)
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompanyWithId(@PathVariable(value="id") Long companyId) {
        return companyRepository.findOne(companyId);
    }

    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    public List<Employee> getEmployeesForCompanyWithId(@PathVariable(value="id") Long companyId) {
        return companyRepository.findOne(companyId).getEmployees();
    }

    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Company> getCompanyInPages(@PathVariable(value="page") int page, @PathVariable(value="pageSize") int pageSize) {
        if (page < 1) return null;
        return companyRepository.findAll(new PageRequest(page - 1, pageSize));
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Company> addCompany(@RequestParam(value = "companyName")String companyName,
                                    @RequestParam(value = "employeesNumber", required = false)Integer employeesNumber) {
        Company company = new Company();
        company.setCompanyName(companyName);

        if (employeesNumber != null) {
            company.setEmployeesNumber(employeesNumber);
        }

        companyRepository.save(company);

        return companyRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public List<Company> updateCompanyWithId(@PathVariable(value = "id") long id,
                                             @RequestParam(value = "companyName", required = false)String companyName,
                                             @RequestParam(value = "employeesNumber", required = false)Integer employeesNumber) {
        Company company = companyRepository.findOne(id);

        // update the company
        if (companyName != null) {
            company.setCompanyName(companyName);
        }

        if (employeesNumber != null) {
            company.setEmployeesNumber(employeesNumber);
        }

        companyRepository.save(company);

        return companyRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public List<Company> deleteCompanyWithId(@PathVariable(value = "id") long id) {
        companyRepository.delete(id);

        return companyRepository.findAll();
    }
}

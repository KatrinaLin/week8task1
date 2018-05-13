package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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


}

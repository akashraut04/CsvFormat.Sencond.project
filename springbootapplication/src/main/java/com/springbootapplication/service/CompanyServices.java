package com.springbootapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootapplication.Exception.ResourceNotFoundException;
import com.springbootapplication.entity.Company;
import com.springbootapplication.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServices {

    @Autowired
    CompanyRepository companyRepository;

    public String readCompanyDetail(int companyId) throws JsonProcessingException, ResourceNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<Company> obj = companyRepository.findById(companyId);
        if (obj.isPresent()) {

            return new ObjectMapper().writeValueAsString(obj.get());

        } else {

            throw new ResourceNotFoundException();

        }

    }

    public List<Company>getAllDetail(){

        return (List<Company>) companyRepository.findAll();
    }

    public List<Company> updateCompany(Company company)
    {

        return (List<Company>) companyRepository.save(company);
    }

    public void deleteCompany(int companyId) {
        companyRepository.deleteById(companyId);
    }
}

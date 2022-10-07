package com.springbootapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootapplication.Exception.ResourceNotFoundException;
import com.springbootapplication.entity.Company;
import com.springbootapplication.entity.Employee;
import com.springbootapplication.repository.CompanyRepository;
import com.springbootapplication.service.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/company")
public class CompanyController {
    @Autowired
    CompanyServices companyServices;

    @GetMapping(path="/companyID/{id}")
    public String getCompanydetail(@PathVariable("id") int companyId){
        ResponseEntity<String> response;
        try {

            String ref = companyServices.readCompanyDetail(companyId);
            return ref;

        }
       catch (ResourceNotFoundException e){
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("No Detail found");
            return response.toString();
        }
        catch( Exception exception) {

            response = ResponseEntity.ok("No data found")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error in processing data");
            return response.toString();
        }
    }

    @RequestMapping(path ="/allcompany")
    public List<Company>getCompayDetail(){

        return companyServices.getAllDetail();
    }

    //update detai

    @PostMapping(path="/update")
    public List<Company> updateDetail(@RequestBody Company company)
    {

       return companyServices.updateCompany(company);

    }

    @DeleteMapping(path="/id/{id}")
    public void deleteDetail(@PathVariable("id") int companyId)
    {
        companyServices.deleteCompany(companyId);
    }

}




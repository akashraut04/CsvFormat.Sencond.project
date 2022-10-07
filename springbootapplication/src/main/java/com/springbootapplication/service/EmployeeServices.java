package com.springbootapplication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootapplication.Bean.EmployeeRequest;
import com.springbootapplication.Exception.ResourceNotFoundException;
import com.springbootapplication.entity.Employee;
import com.springbootapplication.repository.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class EmployeeServices {


    @Autowired
    EmployeeRepository employeeRepository;

    public String readDisplay(int employeeID) throws JsonProcessingException, ResourceNotFoundException {


        ObjectMapper objectMapper = new ObjectMapper();

        Optional<Employee> obj = employeeRepository.findById(employeeID);
        if (obj.isPresent()) {
            return new ObjectMapper().writeValueAsString(obj.get());

        } else {

            throw new ResourceNotFoundException();

        }

    }


    public Boolean saveemployee(EmployeeRequest request) {


        try {
            if (Objects.nonNull(request.getEmployeId())) {
                Optional<Employee> obj = employeeRepository.findById(request.getEmployeId());
                Employee getData = obj.get();
                getData.setEmployeeID(request.getEmployeId());
                getData.setEmployeeName(request.getEmployeeName());
                getData.setCompanyId(request.getCompanyId());
                Employee updateData = employeeRepository.save(getData);

            } else {
                Employee ref = new Employee();
                ref.setEmployeeName(request.getEmployeeName());
                ref.setCompanyId(request.getCompanyId());
                Employee employeeSave = employeeRepository.save(ref);

            }
            return true;
        } catch (Exception e) {

            return false;
        }

    }


    public Boolean updateEmployee(EmployeeRequest newrequest) {


        try {
            if (Objects.nonNull(newrequest.getEmployeId())) {
                Optional<Employee> obj = employeeRepository.findById(newrequest.getEmployeId());
                Employee getData = obj.get();
                getData.setEmployeeID(newrequest.getEmployeId());
                getData.setEmployeeName(newrequest.getEmployeeName());
                getData.setCompanyId(newrequest.getCompanyId());
                Employee updateData = employeeRepository.save(getData);

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean deleteEmployee(int employeeId) throws ResourceNotFoundException {
        Optional<Employee> obj = employeeRepository.findById(employeeId);
        if (obj.isPresent()) {
            employeeRepository.deleteById(employeeId);

        } else {

            throw new ResourceNotFoundException();

        }

        return true;
    }


    public void writeEmployeesToCsv(Writer writer) {

        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getEmployeeID(), employee.getEmployeeName(), employee.getCompanyId());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
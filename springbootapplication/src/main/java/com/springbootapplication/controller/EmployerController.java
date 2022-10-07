package com.springbootapplication.controller;

import com.springbootapplication.Bean.EmployeeRequest;
import com.springbootapplication.Exception.ResourceNotFoundException;
import com.springbootapplication.service.EmployeeServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
    @RequestMapping(path = "/employee")
    public class EmployerController {

    @Autowired
    EmployeeServices employeeServices;
    Logger log= LogManager.getLogger(EmployeeServices.class);
    @RequestMapping(path = "/employeeID/{id}")
    public ResponseEntity<String> getDisplay(@PathVariable("id") int employeeID) {


        ResponseEntity<String> response;
        try {
            String obj = employeeServices.readDisplay(employeeID);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(obj);
            log.info("info log"+response);
            return response;
        }
        catch (ResourceNotFoundException e) {

            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No Detail found");
            log.error("NO data of:- "+response);
            return response;
        } catch (Exception exception) {


            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error in processing data");
            log.error("error while processing"+response);
            return response;
        }
    }

    @PostMapping(path = "/post")
    public ResponseEntity<String> saveDetail(@RequestBody EmployeeRequest newrequest) {


        ResponseEntity<String> response;

        try {

            Boolean obj = (employeeServices.saveemployee(newrequest));
            if (obj) {
                response = ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Succefully save");

            } else {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Error ");

            }
            return response;

        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Data Not Found");
            return response;
        }

    }


    @PutMapping(path = "/put")
    public ResponseEntity<String> updateDetail(@RequestBody EmployeeRequest newrequet) {

        ResponseEntity<String> response;
        try {

            Boolean obj = employeeServices.updateEmployee(newrequet);
            if (obj) {
                response = ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Succefully update");

            } else {
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Error ");

            }
            return response;

        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Data Not Found");
            return response;
        }

    }
    @DeleteMapping(path ="/delete/{id}")
    public ResponseEntity<String> deleteDetail(int employeeId) throws ResourceNotFoundException {

        ResponseEntity<String> response;
        try {
           Boolean  obj = employeeServices.deleteEmployee(employeeId);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Succesfully delete");
            return response;
        } catch (ResourceNotFoundException e) {
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No Detail found");
            return response;
        } catch (Exception exception) {

            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error in processing data");
            return response;
        }

    }
    @GetMapping("/tushardave")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        employeeServices.writeEmployeesToCsv(servletResponse.getWriter());
    }

}

//    @DeleteMapping(path="/deleteid/{id}")
//    public void deleteEmployee(@PathVariable("id") int employeeId)
//    {
//        employeeServices.deleteDetail(employeeId);
//    }






//    @RequestMapping(path = "/allemployee")
//    public List<Employee> getAllEmployye() {
//
//        return employeeServices.getEmployeDetail();
//
//    }
////    @PostMapping(path="/update")
//    public void updateEmployee(@RequestBody Employee employee)
//    {
//
//        employeeServices.updateEmployee(employee);
//
//    }
//
//    @RequestMapping(path="/id/{id}",method=RequestMethod.DELETE )
//    public void deleteDetail(@PathVariable("id") int employeeId)
//    {
//        employeeServices.deleteEmployee(employeeId);
//    }
//   }


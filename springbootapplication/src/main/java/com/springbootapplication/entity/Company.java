package com.springbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springbootapplication.controller.EmployerController;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="company1")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comp_Id")
    int companyID;

EmployerController myempController = new EmployerController();

    @Column(name="comp_name")
    String companyName;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
    @JsonManagedReference
    List<Employee> employees;
}

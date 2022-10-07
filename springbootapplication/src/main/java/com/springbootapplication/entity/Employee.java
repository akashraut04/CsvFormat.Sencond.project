package com.springbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="employee4")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_Id")
    int employeeID;


    @Column(name="employee_name")
    String employeeName;

    @Column(name="company_ID")
    int companyId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id", nullable=false,insertable = false ,updatable = false)
    @JsonBackReference
    Company company;

}

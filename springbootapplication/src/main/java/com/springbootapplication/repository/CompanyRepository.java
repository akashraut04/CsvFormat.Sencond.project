package com.springbootapplication.repository;

import com.springbootapplication.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company,Integer> {
}

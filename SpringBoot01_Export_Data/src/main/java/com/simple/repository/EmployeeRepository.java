package com.simple.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simple.entity.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}

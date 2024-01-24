package com.example.employeeProject.repository;

import com.example.employeeProject.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //@Query(value = "SELECT  * FROM `employee`",nativeQuery = true)
   // Page<Pageable> getAllPagination(Pageable pageable);

    //@Query(value = "SELECT  * FROM `employee`",nativeQuery = true)
   // List<Pageable> getAll(Pageable pageable);

    @Query(value = "SELECT  * FROM `employee`",nativeQuery = true)
    Page<Employee> getAllPagination(Pageable pageable);
}

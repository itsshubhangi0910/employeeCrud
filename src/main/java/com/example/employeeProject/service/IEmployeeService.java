package com.example.employeeProject.service;

import com.example.employeeProject.model.request.EmployeeRequest;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    Object saveOrUpdateEmployee(EmployeeRequest employeeRequest);

    Object getById(Long employeeId) throws Exception;

    Object getByIdEmployee(Long employeeId) throws Exception;

    Object getAllEmployee();

    Object softDeleteEmployee(Long employeeId) throws Exception;



    Object getAllPaginationEmployee(Pageable pageable);
}

package com.example.employeeProject.service.impl;

import com.example.employeeProject.model.Employee;
import com.example.employeeProject.model.request.EmployeeRequest;
import com.example.employeeProject.repository.EmployeeRepository;
import com.example.employeeProject.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Object saveOrUpdateEmployee(EmployeeRequest employeeRequest) {
        if (employeeRepository.existsById(employeeRequest.getEmployeeId())){
            Employee employee=employeeRepository.findById(employeeRequest.getEmployeeId()).get();
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setCity(employeeRequest.getCity());
            employee.setEMail(employeeRequest.getEMail());
            employee.setMobileNo(employeeRequest.getMobileNo());
            employeeRepository.save(employee);
            return "updated";

        }else {
            Employee employee=new Employee();
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setMobileNo(employeeRequest.getMobileNo());
            employee.setCity(employeeRequest.getCity());
            employee.setEMail(employeeRequest.getEMail());
            employee.setActive(true);
            employee.setDeleted(false);
            employeeRepository.save(employee);

        }
        return "save data";
    }

    @Override
    public Object getById(Long employeeId) throws Exception {
        return null;
    }

    @Override
    public Object getByIdEmployee(Long employeeId) throws Exception {
        if (employeeRepository.existsById(employeeId)){
            Employee employee=employeeRepository.findById(employeeId).get();
            return employee;
        }else {
            throw new Exception("id not found");
        }
    }

    @Override
    public Object getAllEmployee() {
        List<Employee> all = employeeRepository.findAll();
        return all;

    }

    @Override
    public Object softDeleteEmployee(Long employeeId) throws Exception {
        if (employeeRepository.existsById(employeeId)){
            Employee employee=employeeRepository.findById(employeeId).get();
            employee.setDeleted(true);
            employeeRepository.save(employee);
            return "deleted";

        }else {
            throw new Exception("id not deleted");
        }
    }

    @Override
    public Object getAllPaginationEmployee(Pageable pageable) {
        Page<Employee> allPagination = employeeRepository.getAllPagination(pageable);
        return allPagination;

    }


}

package com.example.employeeProject.controller;

import com.example.employeeProject.model.request.EmployeeRequest;
import com.example.employeeProject.model.response.CustomResponse;
import com.example.employeeProject.model.response.EntityResponse;
import com.example.employeeProject.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?>saveOrUpdateEmployee(@RequestBody EmployeeRequest employeeRequest){
        try {
            return new ResponseEntity(iEmployeeService.saveOrUpdateEmployee(employeeRequest), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }
    @GetMapping("/getById")
    public ResponseEntity<?>getByIdEmployee(@RequestParam Long employeeId){
        try {
            return new ResponseEntity(new EntityResponse(iEmployeeService.getByIdEmployee(employeeId),0),HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?>getAllEmployee(){
        try {
            return new ResponseEntity(new EntityResponse(iEmployeeService.getAllEmployee(), 0), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @DeleteMapping("/softDelete")
    public ResponseEntity<?>softDeleteEmployee(@RequestParam Long employeeId){
        try {
            return new ResponseEntity(new EntityResponse(iEmployeeService.softDeleteEmployee(employeeId), 0), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }

    }
    @GetMapping("/getAllPagination")
    public ResponseEntity<?>getPagination(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                          @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(new EntityResponse(iEmployeeService.getAllPaginationEmployee(pageable),0),HttpStatus.OK);
        }catch (Exception e ){
            return  new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }


}

package com.example.employeeProject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeRequest {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String eMail;
    private String city;

}

package com.example.employeeProject.service;

import com.example.employeeProject.model.request.UserRequest;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Object userLoginUser(UserRequest userRequest);

    Object changePasswordUser(Long useId, String newPassword, String oldPassword) throws Exception;

    Object startDateEndDateUser(String startDate, String endDate, Pageable pageable);

    Object getAllPaginationUser(Pageable pageable);

    Object getByIdUser(Long userId) throws Exception;

    Object getSearchingUser(String search, Pageable pageable);

}

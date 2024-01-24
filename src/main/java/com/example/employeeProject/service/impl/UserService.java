package com.example.employeeProject.service.impl;

import com.example.employeeProject.model.User;
import com.example.employeeProject.model.request.UserRequest;
import com.example.employeeProject.repository.UserRepository;
import com.example.employeeProject.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Object userLoginUser(UserRequest userRequest) {
        if (userRepository.existsById(userRequest.getUserId())) {
            User user = userRepository.findById(userRequest.getUserId()).get();
            user.setUserName(userRequest.getUserName());
            user.setPassword(userRequest.getPassword());
            userRepository.save(user);
            return "updated";
        } else {
            User user = new User();
            user.setUserName(userRequest.getUserName());
            user.setPassword(userRequest.getPassword());
            user.setActive(true);
            user.setDeleted(false);
            userRepository.save(user);

        }
        return "save data";
    }

    @Override
    public Object changePasswordUser(Long userId, String newPassword, String oldPassword) throws Exception {
        User user = userRepository.findById(userId).get();
        //  String passwordByUserId = userRepository.getPasswordByUserId(userId);
        String oldPasswordDatabse = user.getPassword();
        if (oldPassword.matches(oldPasswordDatabse)) {
            if (newPassword.matches(oldPasswordDatabse)) {
                throw new Exception("New Password not same as old password please use another");

            } else {
                user.setPassword(newPassword);
                userRepository.save(user);
            }
        } else {
            throw new Exception("old Password is incorrect");
        }
        return user;
    }

    @Override
    public Object startDateEndDateUser(String startDate, String endDate, Pageable pageable) {
        Page<User> users;
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            LocalDate startDate1 = LocalDate.parse(startDate);
            LocalDate endDate1 = LocalDate.parse(endDate);

            LocalDateTime startDateTime = LocalDateTime.of(startDate1, LocalTime.of(0, 0));
            LocalDateTime endDateTime = LocalDateTime.of(endDate1, LocalTime.of(23, 59));
            users = userRepository.getStartDateAndEndDate(startDateTime, endDateTime, pageable);
        } else {
            users = userRepository.getAll(pageable);
        }
        return users;
    }

    @Override
    public Object getAllPaginationUser(Pageable pageable) {
        Page<User> all = userRepository.getAll(pageable);
        return all;
    }

    @Override
    public Object getByIdUser(Long userId) throws Exception {
        if (userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).get();
            return user;
        } else {
            throw new Exception("id not found");
        }
    }

    @Override
    public Object getSearchingUser(String search, Pageable pageable) {
        Page<User> users;
        if (search != null) {
            users = userRepository.getAllSearch(search, pageable);
        } else {
            users = userRepository.getAll(pageable);
        }
        return users;
    }
}


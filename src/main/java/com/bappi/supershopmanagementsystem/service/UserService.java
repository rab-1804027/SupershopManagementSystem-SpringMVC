package com.bappi.supershopmanagementsystem.service;

import com.bappi.supershopmanagementsystem.dao.UserDao;
import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.dto.UserInfoDto;
import com.bappi.supershopmanagementsystem.enums.ApprovalStatus;
import com.bappi.supershopmanagementsystem.model.Product;
import com.bappi.supershopmanagementsystem.model.User;
import com.bappi.supershopmanagementsystem.utils.PasswordHashing;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        user.setPassword(PasswordHashing.hashPassword(user.getPassword()));
        userDao.save(user);
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public UserDto findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public UserDto findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public List<Product> findProductsByUserId(int userId) {
        User user = userDao.findById(userId);
        return user.getProductList();
    }

    public void assignRole(String username, String role, ApprovalStatus status) {
        userDao.assignRole(username, role, status);
    }

    public List<UserInfoDto> findByApprovalStatus(ApprovalStatus status) {
        return userDao.findByApprovalStatus(status);
    }
}

package com.haapp.jdeath.service;

import com.haapp.jdeath.dao.UserDao;
import com.haapp.jdeath.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(){
        this.userDao = new UserDao();
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public User getUserWithMaxAccount() throws SQLException {
        return userDao.getUserWithMaxAccount();
    }

    public Integer getSumOfAllAccounts() throws SQLException {
        return userDao.getSumOfAllAccounts();
    }
}

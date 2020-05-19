package com.haapp.jdeath.dao;

import com.haapp.jdeath.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/jdeath?characterEncoding=utf-8&useUnicode=true";
    private final String USERNAME = "root";
    private final String PASSWORD = "Aa123456";
    private Connection connection;

    protected void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(DRIVER_CLASS_NAME);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
    }

    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        connect();
        String sql = "SELECT u.userid, u.name, u.sureName, SUM(a.account) AS account FROM user u LEFT JOIN account a ON u.userid = a.userid GROUP BY u.userid";
        List<User> listUser = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Long userid = resultSet.getLong("userid");
            String name = resultSet.getString("name");
            String sureName = resultSet.getString("sureName");
            Integer account = resultSet.getInt("account");
            User user = new User(userid, name, sureName, account);
            listUser.add(user);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listUser;
    }

    public User getUserById(Long userid) throws SQLException {
        connect();
        User user = null;
        String sql = "SELECT u.userid, u.name, u.sureName, SUM(a.account) AS account FROM jdeath.user u LEFT JOIN jdeath.account a ON u.userid = a.userid GROUP BY u.userid HAVING u.userid=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userid);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String sureName = resultSet.getString("sureName");
            Integer account = resultSet.getInt("account");
            user = new User(userid, name, sureName, account);
        }
        resultSet.close();
        statement.close();
        return user;
    }

    public User getUserWithMaxAccount() throws SQLException {
        User user = null;
        String sql = "SELECT u.userid, u.name, u.sureName, SUM(a.account) AS account FROM user u LEFT JOIN account a ON u.userid = a.userid GROUP BY u.userid ORDER BY account DESC LIMIT 1";
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Long userid = resultSet.getLong("userid");
            String name = resultSet.getString("name");
            String sureName = resultSet.getString("sureName");
            Integer account = resultSet.getInt("account");
            user = new User(userid, name, sureName, account);
            resultSet.close();
            statement.close();
            return user;
        }
        return null;
    }

    public Integer getSumOfAllAccounts() throws SQLException {
        String sql = "SELECT SUM(account) FROM account";
        connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Integer accountSum = resultSet.getInt("SUM(account)");
            resultSet.close();
            statement.close();
            return accountSum;
        }
        return null;
    }
}

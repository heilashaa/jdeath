package com.haapp.jdeath.servlet;

import com.haapp.jdeath.model.User;
import com.haapp.jdeath.service.UserService;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    private UserService userService;

    public void init() {
        this.userService = new UserService();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        if (param!= null && param.equals("richestUser")){
            User richestUser = userService.getUserWithMaxAccount();
            request.setAttribute("richestUser", richestUser);
        } else if (param!= null && param.equals("accountsSum")) {
            Integer accountsSum = userService.getSumOfAllAccounts();
            request.setAttribute("accountsSum", accountsSum);
        }
        request.setAttribute("param", param);
        userList(request, response);
    }

    private void userList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<User> userList = userService.getAllUsers();
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
        dispatcher.forward(request, response);
    }
}

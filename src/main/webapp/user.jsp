<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <div align="center">
        <h1>Users</h1>
        <h2>
            <a href="<%=application.getContextPath()%>/user?param=richestUser">Richest user</a>
            <a href="<%=application.getContextPath()%>/user?param=accountsSum">Accounts sum</a>
        </h2>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surename</th>
                <th>Account</th>
            </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.userid}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.sureName}" /></td>
                    <td><c:out value="${user.account}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <c:if test="${richestUser != null}">
        <div align="center" style="color: red">
            <h2>Richest user is <c:out value='${richestUser.name}'/> with id = <c:out value='${richestUser.userid}'/></h2>
        </div>
    </c:if>
    <c:if test="${accountsSum != null}">
        <div align="center" style="color: red">
            <h2>Account sum is: <c:out value='${accountsSum}' /></h2>
        </div>
    </c:if>
</body>
</html>

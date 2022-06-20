<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 13.04.2022
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Employees</title>
</head>
<body>
    <h2>Employee List</h2>
    <br>
    <Table border="1">
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Department</th>
            <th>Salary</th>
            <th>Operations</th>
        </tr>
        <c:forEach var="emp" items="${allEmps}">
            <c:url var="updateButton" value="/update">
                <c:param name="empId" value="${emp.id}"/>
            </c:url>
            <c:url var="deleteButton" value="/delete">
                <c:param name="empId" value="${emp.id}"/>
            </c:url>
            <tr>
                <td>${emp.name}</td>
                <td>${emp.surname}</td>
                <td>${emp.department}</td>
                <td>${emp.salary}</td>
                <td>
                    <input type="button" value="Update" onclick="window.location.href='${updateButton}'"/>
                    <input type="button" value="Delete" onclick="window.location.href='${deleteButton}'"/>
                </td>
            </tr>
        </c:forEach>
    </Table>
    <br>
    <input type="button" value="Add" onclick="window.location.href='/new'"/>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.ks.shtil.java.db.DepartmentDBManager" %>
<%@ page import="ua.ks.shtil.java.models.Position" %>
<%--
  Created by IntelliJ IDEA.
  User: shtil
  Date: 19.06.14
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New user</title>
</head>
<body>

<%
    List<Department> departmentList = new ArrayList<>();
    List<Position> positionList = new ArrayList<>();
    DepartmentDBManager departmentDBManager = new DepartmentDBManager();
    departmentList = departmentDBManager.getAllDepartments();
    positionList = departmentDBManager.getAllPositions();


%>
Edit user

Size = <%=positionList.size()%>

<form action="addUser" method="POST">
    User Name: <input type="text" name="name" value=""><br/>
    User Birthday: <input type="date" name="birthday" value=""><br/>
    User Passport number: <input type="text" name="passportNumber" value=""><br/>
    User Department:
    <select name="department">
        <% for (Department department : departmentList) {%>
        <option  value="<%=department.getId()%>"><%=department.getName()%>
        </option>
        <%}%>
    </select>
    <br/>
    User Position:
    <select name="position">
        <% for (Position position : positionList) {%>
        <option  value="<%=position.getId()%>"><%=position.getName()%>
        </option>
        <%}%>
    </select>
    <br/>
    Salary: <input type="text" name="salary" value=""><br/>

    <input type="submit" value="Save"/>
</form>
</body>
</html>

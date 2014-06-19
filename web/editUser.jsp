<%@ page import="ua.ks.shtil.java.models.Employee" %>
<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: shtil
  Date: 18.06.14
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>

<% Employee employee = (Employee) request.getAttribute("employee");
    List<Department> departmentList = (List<Department>) request.getAttribute("departments");
%>
Edit user

<form action="editUser" method="POST">
    <input type="hidden" name="id" value="<%=employee.getId()%>"><br/>
    User Name: <input type="text" name="name" value="<%=employee.getName()%>"><br/>
    User Birthday: <input type="date" name="birthday" value="<%=employee.getBirthday()%>"><br/>
    User Passport number: <input type="text" name="passportNumber" value="<%=employee.getPassportNumber()%>"><br/>
    User Department:
    <select name="department">
        <% for (Department department : departmentList) {%>
        <option <% if (department.getId() == employee.getDepartment()) {
        %> selected <%}%>
           value="<%=department.getId()%>"><%=department.getName()%>
        </option>
        <%}%>
    </select>
    <br/>
    Salary: <input type="text" name="salary" value="<%=employee.getSalary()%>"><br/>

    <input type="submit" value="Save"/>

</form>
</body>
</html>





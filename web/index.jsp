<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.ks.shtil.java.models.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: shtil
  Date: 16.06.14
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>

<% List<Employee> employeeList = (List<Employee>) request.getAttribute("employees");
    List<Department> departments = (List<Department>) request.getAttribute("departments");
    Department department = (Department) request.getAttribute("department");
%>


<table border="1px" width="600" align="center">
    <tr>
        <td width="200">Departments<br/>

            <% for (Department dep : departments) {%>
            <a href="index?dep=<%=dep.getId()%>"><%=dep.getName()%>
            </a><br/>
            <%}%>

            <br/><br/><br/><br/>
            <a href="addDepartment">Add Department</a>
            <br/><br/>
            <a href="addUser.jsp">Add Employee</a>
        </td>
        <td width="400">
            <% if (employeeList == null ) {%>
            No users =(<br/>
            <% } else { %>
            Users of <%=department.getName()%>

            <% for (Employee employee : employeeList) {%>
            ======================================== <br/>
            id = <%=employee.getId()%><br/>
            name = <%=employee.getName()%><br/>
            <a href="editUser?id=<%=employee.getId()%>">edit</a><br/><br/>

            <%}%>
            <%}%>

        </td>
    </tr>
</table>

</body>
</html>

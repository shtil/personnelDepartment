<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="ua.ks.shtil.java.db.DepartmentDBManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: shtil
  Date: 18.06.14
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%
    List<Department> departments = new ArrayList<Department>();
    DepartmentDBManager departmentDBManager = new DepartmentDBManager();
    departments = departmentDBManager.getAllDepartments();
%>


<table border="1px" width="600" align="center">
    <tr>
        <td width="200">Departments<br/>

            <% for (Department dep : departments) {%>
            <a href="index?dep=<%=dep.getId()%>"><%=dep.getName()%></a><br/>
            <%}%>

            <br/><br/><br/><br/>
            <a href="addDepartment">Add Department</a>
            <br/><br/>
            <a href="addUser.jsp">Add Employee</a>
        </td>
        <td width="400">

            <form action="addDepartment" method="POST">
                Department name: <input type="text" name="name">
                <br />
                <input type="submit" value="Submit" />
            </form>

        </td>
    </tr>
</table>

</body>
</html>

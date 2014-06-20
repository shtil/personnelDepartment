<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.ks.shtil.java.models.Employee" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Departments</title>
    <link rel="stylesheet" type="text/css" HREF="<%=request.getContextPath()%>/style/style.css"/>
</head>
<body>

<div id="main_container">
    <div id="header">
        <div class="logo"></div>
    </div>
    <div class="menu"></div>

    <div class="center_content">

        <div class="center_left">
            <div class="title_welcome">Personnel department</div>
            <div class="welcome_box">

                <% List<Employee> employeeList = (List<Employee>) request.getAttribute("employees");
                    List<Department> departments = (List<Department>) request.getAttribute("departments");
                    Department department = (Department) request.getAttribute("department");
                %>
                <table border="1px" width="600" align="center" >
                    <tr>
                        <td width="200"">

                            <div class="title">Departments</div>
                            <br/>

                            <% for (Department dep : departments) {%>
                            <a href="index?dep=<%=dep.getId()%>"><%=dep.getName()%>
                            </a><br/>
                            <%}%>

                            <br/><br/><br/><br/>
                            <a href="addDepartment" class="read_more">Add Department</a>
                            <br/><br/>
                            <a href="addUser.jsp" class="read_more">Add Employee</a>
                        </td>
                        <td width="400">
                            <% if (employeeList == null) {%>
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

            </div>
        </div>
    </div>

    <div id="footer">
        <div class="left_footer"> Author Aleksander Milchenko <a href="mailto:shtil88@gmail.com">shtil88@gmail.com</a>
        </div>
    </div>
</div>

<!-- end of main_container -->
</body>
</html>





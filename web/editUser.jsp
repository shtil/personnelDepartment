<%@ page import="ua.ks.shtil.java.models.Employee" %>
<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.List" %>



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

                <%
                    Employee employee = (Employee) request.getAttribute("employee");
                    List<Department> departmentList = (List<Department>) request.getAttribute("departments");
                %>

                <table border="1px" width="600" align="center">
                    <tr>
                        <td width="200">

                            <div class="title">Departments</div>
                            <br/>

                            <% for (Department dep : departmentList) {%>
                            <a href="index?dep=<%=dep.getId()%>"><%=dep.getName()%>
                            </a><br/>
                            <%}%>

                            <br/><br/><br/><br/>
                            <a href="addDepartment" class="read_more">Add Department</a>
                            <br/><br/>
                            <a href="addUser.jsp" class="read_more">Add Employee</a>
                        </td>
                        <td width="400">

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
</div>
<!-- end of main_container -->

</body>
</html>


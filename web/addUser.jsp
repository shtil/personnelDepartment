<%@ page import="java.util.List" %>
<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.ks.shtil.java.db.DepartmentDBManager" %>
<%@ page import="ua.ks.shtil.java.models.Position" %>
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
                    List<Department> departmentList = new ArrayList<>();
                    List<Position> positionList = new ArrayList<>();
                    DepartmentDBManager departmentDBManager = new DepartmentDBManager();
                    departmentList = departmentDBManager.getAllDepartments();
                    positionList = departmentDBManager.getAllPositions();


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
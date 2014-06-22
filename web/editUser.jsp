<%@ page import="ua.ks.shtil.java.models.Employee" %>
<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Departments</title>
    <link rel="stylesheet" type="text/css" HREF="<%=request.getContextPath()%>/style/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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


            <%
                Employee employee = (Employee) request.getAttribute("employee");
                List<Department> departments = (List<Department>) request.getAttribute("departments");
                String warning = (String) request.getAttribute("warning");
            %>


            <div id="content">
                <div id="left">

                    <div class="title">Departments</div>
                    <br/>

                    <ul>
                        <% for (Department dep : departments) {%>
                        <li><a href="index?dep=<%=dep.getId()%>"><%=dep.getName()%>
                        </a></li>
                        <%}%>
                    </ul>

                    <br/><br/><br/>

                    <div style="text-align: center; display: table; margin: 0 auto;">
                        <a href="addDepartment" class="btn_add">Add Department</a>
                        <br/><br/>
                        <a href="addUser" class="btn_add">Add Employee</a>
                        <br/><br/>
                        <a href="addPosition" class="btn_add">Add Position</a>
                    </div>
                </div>
                <div id="right">
                    <div class="title">Editing employee</div>
                    <br/>
                    <% if  (warning!=null){%>
                    <div class="err"><%=warning%></div>
                    <%}%>

                    <form action="editUser" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%=employee.getId()%>"><br/>
                        User Name: <input type="text" name="name" value="<%=employee.getName()%>"><br/>
                        User Birthday: <input type="date" name="birthday" value="<%=employee.getBirthday()%>"><br/>
                        User Passport number: <input type="text" name="passportNumber" value="<%=employee.getPassportNumber()%>"><br/>
                        User Department:
                        <select name="department">
                            <% for (Department department : departments) {%>
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

                </div>
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

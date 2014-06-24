<%@ page import="ua.ks.shtil.java.models.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.ks.shtil.java.models.Employee" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Departments</title>
    <link rel="stylesheet" type="text/css" HREF="<%=request.getContextPath()%>/style/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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


            <% List<Employee> employeeList = (List<Employee>) request.getAttribute("employees");
                List<Department> departments = (List<Department>) request.getAttribute("departments");
                Department department = (Department) request.getAttribute("department");
            %>

            <div id="content">
                <div id="left">

                    <div class="title">Departments</div>
                    <br/>

                    <ul>
                        <% for (Department dep : departments) {%>
                        <li><a href="index?dep=<%=dep.getId()%>"><%=dep.getName()%> </a></li>
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

                    <% if (employeeList == null) {%>
                    <div class="title">Please select the department</div>
                    <br/>
                    <% } else { %>
                    <div class="title">Users of <%=department.getName()%>
                    </div>
                    <br/>

                    <% for (Employee employee : employeeList) {%>
                    <div style=" display: table; margin: 0 auto; width: 390px; padding-bottom: 5px;">
                    <div style=" float:left;"><strong><%=employee.getName()%></strong><br/>
                        <%=employee.getPosition().getName()%>
                    </div>
                        <div style="float: right;"><a class="btn" onclick="return confirm('Are you sure you want to delete this item?');"
                                                      href="deleteUser?id=<%=employee.getId()%>&dep=<%=employee.getDepartment().getId()%>">Delete</a>
                        </div>
                        <div style="float: right; margin-right: 5px;"><a class="btn"
                                                                         href="editUser?id=<%=employee.getId()%>">Edit</a>
                        </div>
                    </div>
                    <%}%>


                    <%}%>

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





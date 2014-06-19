package ua.ks.shtil.java.servlets;

import ua.ks.shtil.java.db.DepartmentDBManager;
import ua.ks.shtil.java.models.Department;
import ua.ks.shtil.java.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shtil on 14.06.14.
 */

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameterMap().containsKey("dep")){
            showSelectedDepartment(request, response);
        } else {
            showDefaultPage(request, response);
        }



    }

    private void showDefaultPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Department> departments = new ArrayList<>();
        Department department = new Department();

        DepartmentDBManager departmentDBManager = new DepartmentDBManager();


        try {
            departments = departmentDBManager.getAllDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("department", department);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    private void showSelectedDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depParam = request.getParameter("dep");

        int departmentId = Integer.parseInt(depParam);

        List<Employee> employees = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        Department department = new Department();

        DepartmentDBManager departmentDBManager = new DepartmentDBManager();

        try {
            employees = departmentDBManager.getAllEmployye(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            departments = departmentDBManager.getAllDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            department = departmentDBManager.getDepartment(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("department", department);
        request.setAttribute("employees", employees);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

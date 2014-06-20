package ua.ks.shtil.java.servlets;

import ua.ks.shtil.java.db.DepartmentDBManager;
import ua.ks.shtil.java.models.Department;
import ua.ks.shtil.java.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by shtil on 18.06.14.
 */
public class editUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String depParam = req.getParameter("id");
        System.out.println("id = " + depParam);
        int userId = Integer.parseInt(depParam);

        Employee employee = new Employee();
        List<Department> departments = new ArrayList<>();
        DepartmentDBManager departmentDBManager = new DepartmentDBManager();

        try {
            employee = departmentDBManager.getEmployee(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            departments = departmentDBManager.getAllDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(employee.toString());

        req.setAttribute("employee", employee);
        req.setAttribute("departments", departments);
        req.getRequestDispatcher("editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");
        String passportNumber = req.getParameter("passportNumber");
        String reqDepId = req.getParameter("department");
        int department = Integer.parseInt(reqDepId);
        BigDecimal salary = new BigDecimal(req.getParameter("salary"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(req.getParameter("birthday"));
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setBirthday(date);
        employee.setPassportNumber(passportNumber);
        employee.setDepartment(department);
        employee.setSalary(salary);

        DepartmentDBManager departmentDBManager = new DepartmentDBManager();
        try {
            departmentDBManager.updateUser(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }

      //  doGet(req, resp);
        req.getRequestDispatcher("index").forward(req, resp);
    }
}

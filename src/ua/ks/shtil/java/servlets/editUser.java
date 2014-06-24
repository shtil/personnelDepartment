package ua.ks.shtil.java.servlets;

import ua.ks.shtil.java.db.DepartmentDBManager;
import ua.ks.shtil.java.models.Department;
import ua.ks.shtil.java.models.Employee;
import ua.ks.shtil.java.models.Position;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

/**
 * Created by shtil on 18.06.14.
 */
public class editUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId =0;

        try {
             userId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Employee employee = new Employee();
        List<Department> departments = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
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

        try {
            positions = departmentDBManager.getAllPositions();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("employee", employee);
        req.setAttribute("departments", departments);
        req.setAttribute("positions", positions);
        req.getRequestDispatcher("editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        int department = 0;
        int position = 0;
        BigDecimal salary = null;


        if (req.getParameter("department")!=null) {
            try {
                department = Integer.parseInt(req.getParameter("department"));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        if (req.getParameter("position")!=null) {
            try {
                position = Integer.parseInt(req.getParameter("position"));
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        if (req.getParameter("salary")!=null) {
            try {
                salary = new BigDecimal(req.getParameter("salary"));
            } catch ( NumberFormatException e){
                e.printStackTrace();
            }
        }


        if (id ==0 || department==0 || position == 0 || salary == null){

            String warning = "Please write correct data";
            req.setAttribute("warning", warning);
            doGet(req,resp);
            return;
        }
        DepartmentDBManager departmentDBManager = new DepartmentDBManager();
        Position mPosition = new Position();
        Department mDepartment = new Department();
        Employee employee = new Employee();
        employee.setId(id);

        try {
            mDepartment = departmentDBManager.getDepartment(position);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            mPosition = departmentDBManager.getPosition(position);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        employee.setDepartment(mDepartment);
        employee.setPosition(mPosition);
        employee.setSalary(salary);

        try {
            departmentDBManager.updateUser(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String url = "index?dep=" + employee.getDepartment().getId();

        req.getRequestDispatcher(url).forward(req, resp);
    }
}

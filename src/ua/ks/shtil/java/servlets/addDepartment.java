package ua.ks.shtil.java.servlets;

import ua.ks.shtil.java.db.DepartmentDBManager;
import ua.ks.shtil.java.models.Department;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shtil on 16.06.14.
 */
public class addDepartment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        main(req, resp);

    }

    private void main(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = new ArrayList<>();

        DepartmentDBManager departmentDBManager = new DepartmentDBManager();

        try {
            departments = departmentDBManager.getAllDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("departments", departments);
        req.getRequestDispatcher("addDepartment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String departmentName = req.getParameter("name");
        if (departmentName != null) {

            DepartmentDBManager departmentDBManager = new DepartmentDBManager();
            try {
                departmentDBManager.saveNewDepartment(departmentName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;
        }
        main(req, resp);


    }
}

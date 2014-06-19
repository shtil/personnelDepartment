package ua.ks.shtil.java.servlets;

import ua.ks.shtil.java.db.DepartmentDBManager;
import ua.ks.shtil.java.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shtil on 19.06.14.
 */
public class addUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String birthday = req.getParameter("birthday");
        String passportNumber = req.getParameter("passportNumber");
        int department = Integer.parseInt(req.getParameter("department"));
        int position = Integer.parseInt(req.getParameter("position"));
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
        employee.setName(name);
        employee.setBirthday(date);
        employee.setPassportNumber(passportNumber);
        employee.setDepartment(department);
        employee.setSalary(salary);

        System.out.println(employee);

        DepartmentDBManager departmentDBManager = new DepartmentDBManager();
        try {
            departmentDBManager.saveNewUser(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("index").forward(req, resp);
       // doGet(req,resp);
    }
}

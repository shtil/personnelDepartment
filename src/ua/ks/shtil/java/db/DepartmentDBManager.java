package ua.ks.shtil.java.db;

/**
 * Created by shtil on 11.06.14.
 */

import ua.ks.shtil.java.models.Department;
import ua.ks.shtil.java.models.Employee;
import ua.ks.shtil.java.models.Position;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class DepartmentDBManager {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    /* private DepartmentDBManager()

     {

     }

     public static synchronized ManagementSystem getInstance()

     {

         if (instance == null) {

             try {

                 instance = new ManagementSystem();

                 Context ctx = new InitialContext();

                 instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentsDS");

                 con = dataSource.getConnection();

             }

             catch (NamingException e) {

                 e.printStackTrace();

             }

             catch (SQLException e) {

                 e.printStackTrace();

             }

         }

         return instance;

     }
 */
    public DepartmentDBManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/personnel_department", "root", "shtil27101988");
    }


    public List<Position> getAllPositions() throws SQLException {

        List<Position> positions = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT * FROM position");

            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getInt("id"));
                position.setName(resultSet.getString("name"));
                position.setMinSalary(resultSet.getBigDecimal("minSalary"));
                position.setMaxSalary(resultSet.getBigDecimal("minSalary"));
                position.setDepartment(resultSet.getInt("department_id"));
                positions.add(position);
                }
        }
        return positions;
    }

    public List<Department> getAllDepartments() throws SQLException {

        List<Department> departments = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT * FROM department");

            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                departments.add(department);
            }
        }
        return departments;
    }

    public void saveNewDepartment(String name) throws SQLException {


        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            PreparedStatement stmt = c.prepareStatement( "INSERT INTO department  (name) VALUES (?)");
            stmt.setString(1, name);
            stmt.execute();
        }

    }

    public void updateUser(Employee employee) throws SQLException {
        try (Connection c = getConnection();
            PreparedStatement stmt = c.prepareStatement( "UPDATE employee SET name = (?), birthday = (?), passportNumber = (?), department_id = (?),position_id = (?), salary = (?)  WHERE  id= (?)");
        ) {
            stmt.setString(1,employee.getName());
            stmt.setDate(2, new Date(employee.getBirthday().getDate()));
            stmt.setString(3, employee.getPassportNumber());
            stmt.setInt(4, employee.getDepartment());
            stmt.setInt(5, employee.getPosition());
            stmt.setBigDecimal(6, employee.getSalary());
            stmt.setInt(7, employee.getId());

            stmt.execute();
        }
    }
    public void saveNewUser(Employee employee) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement stmt = c.prepareStatement( "INSERT INTO employee  (name, birthday, passportNumber,department_id, position_id, salary) VALUES (?,?,?,?,?,?)");
        ) {
            stmt.setString(1,employee.getName());
            stmt.setDate(2, new Date(employee.getBirthday().getDate()));
            stmt.setString(3, employee.getPassportNumber());
            stmt.setInt(4, employee.getDepartment());
            stmt.setInt(5, employee.getPosition());
            stmt.setBigDecimal(6, employee.getSalary());

            stmt.execute();
        }
    }

    public Department getDepartment(int departmentID) throws SQLException {

        Department department = new Department();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT * FROM department WHERE id =" + departmentID);

            while (resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }
        }
        return department;
    }


    public List<Employee> getAllEmployye(int departmentID) throws SQLException {

        List<Employee> employees = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT * FROM employee WHERE department_id =" + departmentID);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setBirthday(resultSet.getDate("birthday"));
                employee.setPassportNumber(resultSet.getString("passportNumber"));
                employee.setDepartment(resultSet.getInt("department_id"));
                employee.setPosition(resultSet.getInt("position_id"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                employees.add(employee);
            }
        }
        return employees;
    }


    public Employee getEmployee(int employeeId) throws SQLException {

        Employee employee = new Employee();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT * FROM employee WHERE id =" + employeeId);

            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setBirthday(resultSet.getDate("birthday"));
                employee.setPassportNumber(resultSet.getString("passportNumber"));
                employee.setDepartment(resultSet.getInt("department_id"));
                employee.setPosition(resultSet.getInt("position_id"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
            }
        }
        return employee;
    }




    // you need to close all three to make sure
    private void close() {
      /*  close(resultSet);
        close(statement);
        close(connect);
    */
    }

    private void close(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            // don't throw now as it might leave following closables in undefined state
        }
    }
}

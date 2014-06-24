package ua.ks.shtil.java.db;

/**
 * Created by shtil on 11.06.14.
 */

import ua.ks.shtil.java.models.Department;
import ua.ks.shtil.java.models.Employee;
import ua.ks.shtil.java.models.Position;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
       // return DriverManager.getConnection("jdbc:mysql://localhost/personnel_department", "root", "shtil27101988");
        //DriverManager.getConnection("jdbc:mysql:///dbname?useUnicode=true&characterEncoding=utf-8", "user", "pass");
        Properties properties = new Properties();

        String dbName = null;
        String user = null;
        String password = null;
        try {
           // properties.load(this.getClass().getClassLoader().getResourceAsStream("/WEB-INF/db.properties"));
            InputStream inputStream = ClassLoader
                    .getSystemResourceAsStream("/db.properties");

            properties.load(inputStream);

            dbName = properties.getProperty("dbname");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            System.out.println( "property =" + dbName + " " + user + " "+ password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  DriverManager.getConnection("jdbc:mysql://localhost/personnel_department?useUnicode=true&characterEncoding=UTF-8", "shtil", "shtil27101988");
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
                position.setMaxSalary(resultSet.getBigDecimal("maxSalary"));
                position.setDepartment(resultSet.getInt("department"));
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
            PreparedStatement stmt = c.prepareStatement( "UPDATE employee SET department_id = (?), position_id = (?), salary = (?)  WHERE  id= (?)");
        ) {
            stmt.setInt(1, employee.getDepartment().getId());
            stmt.setInt(2, employee.getPosition().getId());
            stmt.setBigDecimal(3, employee.getSalary());
            stmt.setInt(4, employee.getId());

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
            stmt.setInt(4, employee.getDepartment().getId());
            stmt.setInt(5, employee.getPosition().getId());
            stmt.setBigDecimal(6, employee.getSalary());

            stmt.execute();
        }
    }

    public void saveNewPosition(Position position) throws  SQLException{

        try (Connection c = getConnection();
             PreparedStatement stmt = c.prepareStatement( "INSERT INTO position (name,department,minSalary,maxSalary ) VALUES (?,?,?,?)");
        ) {
            stmt.setString(1,position.getName());
            stmt.setInt(2, position.getDepartment());
            stmt.setBigDecimal(3, position.getMinSalary());
            stmt.setBigDecimal(4, position.getMaxSalary());

            stmt.execute();
        }
    }

    public Department getDepartment(int departmentId) throws SQLException {

        Department department = new Department();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT * FROM department WHERE id =" + departmentId);

            while (resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }
        }
        return department;
    }

    public Position getPosition(int positionId) throws SQLException {

        Position position = new Position();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT * FROM position WHERE id =" + positionId);

            while (resultSet.next()) {
                position.setId(resultSet.getInt("id"));
                position.setName(resultSet.getString("name"));
                position.setMinSalary(resultSet.getBigDecimal("minSalary"));
                position.setMaxSalary(resultSet.getBigDecimal("maxSalary"));
            }
        }
        return position;
    }


/*
    public List<Employee> getAllEmployee(int departmentID) throws SQLException {

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
*/


    public List<Employee> getAllEmployee(int departmentID) throws SQLException {

        List<Employee> employees = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ) {
            ResultSet resultSet = st
                    .executeQuery("SELECT e.id, e.name, e.birthday, e.passportNumber, e.salary,e.position_id, d.id, d.name, p.id, p.name, p.department, p.minSalary, p.maxSalary FROM employee e INNER JOIN department d ON e.department_id=d.id INNER JOIN position p ON e.position_id=p.id WHERE e.department_id =" +departmentID);

            while (resultSet.next()) {
                Employee employee = new Employee();
                Department department = new Department();
                Position position = new Position();

                employee.setId(resultSet.getInt("e.id"));
                employee.setName(resultSet.getString("e.name"));
                employee.setBirthday(resultSet.getDate("e.birthday"));
                employee.setPassportNumber(resultSet.getString("e.passportNumber"));

                department.setId(resultSet.getInt("d.id"));
                department.setName(resultSet.getString("d.name"));

                employee.setDepartment(department);

                position.setId(resultSet.getInt("p.id"));
                position.setName(resultSet.getString("p.name"));
                position.setDepartment(resultSet.getInt("department"));
                position.setMinSalary(resultSet.getBigDecimal("p.minSalary"));
                position.setMaxSalary(resultSet.getBigDecimal("p.maxSalary"));

                employee.setPosition(position);

                employees.add(employee);

                System.out.println(employee.toString());
                System.out.println(department.toString());
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
                    .executeQuery("SELECT e.id, e.name, e.birthday, e.passportNumber, e.salary,e.position_id, d.id, d.name, p.id, p.name, p.department, p.minSalary, p.maxSalary FROM employee e INNER JOIN department d ON e.department_id=d.id INNER JOIN position p ON e.position_id=p.id WHERE e.id = "+employeeId);
            //  .executeQuery("SELECT * FROM employee WHERE id =" + employeeId);

            while (resultSet.next()) {

                Department department = new Department();
                Position position = new Position();

                employee.setId(resultSet.getInt("e.id"));
                employee.setName(resultSet.getString("e.name"));
                employee.setBirthday(resultSet.getDate("e.birthday"));
                employee.setPassportNumber(resultSet.getString("e.passportNumber"));

                department.setId(resultSet.getInt("d.id"));
                department.setName(resultSet.getString("d.name"));

                employee.setDepartment(department);

                position.setId(resultSet.getInt("p.id"));
                position.setName(resultSet.getString("p.name"));
                position.setDepartment(resultSet.getInt("department"));
                position.setMinSalary(resultSet.getBigDecimal("p.minSalary"));
                position.setMaxSalary(resultSet.getBigDecimal("p.maxSalary"));

                employee.setPosition(position);



                System.out.println(employee.toString());
                System.out.println(department.toString());
                System.out.println();
            }
        }
        return employee;
    }

    public void removeEmployee(int id)  throws SQLException{

        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection c = getConnection();
             PreparedStatement stmt = c.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            stmt.execute();
        }
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

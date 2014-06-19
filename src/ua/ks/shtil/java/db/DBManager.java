package ua.ks.shtil.java.db;

import ua.ks.shtil.java.models.Department;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shtil on 14.06.14.
 */
public class DBManager {

    public DBManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<Department> getAllDepartments () throws SQLException {
        List<Department> result = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement()
        ){
            ResultSet rs =
                    st.executeQuery("select id, name from department");
            while (rs.next()){
                Department m = new Department(rs.getInt(1), rs.getString(2));

                result.add(m);
            }
        }
        return result;
    }


    private Connection getConnection() throws SQLException {
        try {

            Context initialContext = new InitialContext();
            DataSource datasource =
                    (DataSource) initialContext.lookup("java:comp/env/jdbc/TestDB");
            if (datasource != null) {
                return datasource.getConnection();
            }
        } catch (NamingException ex) {
            throw new SQLException("Cannot find datasource", ex);
        }
        return null;
    }
   /* private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/personnel_department", "root", "shtil27101988");
    }*/
}

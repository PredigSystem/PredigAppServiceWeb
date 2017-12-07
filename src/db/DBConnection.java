package db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

    private static DataSource dataSource;

    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:jboss/PostbresXA");
        }
        catch (NamingException e) {
            throw new ExceptionInInitializerError("'java:jboss/PostbresXA' not found!");
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}

package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/database_pp";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "ROOT-sql_2022";

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public Util() {
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class);

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


        return sessionFactory = configuration
                .addProperties(settings)
                .buildSessionFactory();

    }
}

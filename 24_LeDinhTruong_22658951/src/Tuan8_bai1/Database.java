package Tuan8_bai1;

import java.sql.*;

public class Database {
    public static Connection con = null;
    private static Database instance = new Database();

    public static Database getInstance() {
        return instance;
    }

    public void connect() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsv";
        String user = "sa";
        String password = "sapassword";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}

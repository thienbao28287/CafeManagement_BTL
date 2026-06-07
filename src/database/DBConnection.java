package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
            + "instanceName=SQLEXPRESS;" 
            + "databaseName=quanLyCaPhe;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"; 

    private static final String USER = "sa";
    private static final String PASSWORD = "123";


    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy thư viện JDBC Driver trong dự án!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            return null;
        }
    }

  
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
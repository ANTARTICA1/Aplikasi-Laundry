package aplikasilaundry;

import java.sql.*;
import javax.swing.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://192.168.1.4:3306/laundrydb";
                String user = "laundry";
                String pass = "laundry123";
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi Berhasil!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
            }
        }
        return conn;
    }
    
    public static void main(String args[]){
        getConnection();
    }
    
}


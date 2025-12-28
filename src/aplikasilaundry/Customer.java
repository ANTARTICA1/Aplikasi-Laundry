package aplikasilaundry;

import java.sql.*;
import javax.swing.JOptionPane;

public class Customer {

    public static int insert(String nama, String noHp) {
        int idCustomer = 0;
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO customer (nama, no_hp) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nama);
            ps.setString(2, noHp);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idCustomer = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idCustomer;
    }

    // UPDATE NAMA CUSTOMER BERDASARKAN ID
    public static void updateNama(int idCustomer, String namaBaru) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE customer SET nama=? WHERE id_customer=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, namaBaru);
            ps.setInt(2, idCustomer);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal update nama customer!");
        }
    }

    public static int getIdByName(String nama) {
        int idCustomer = -1;
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT id_customer FROM customer WHERE nama=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idCustomer = rs.getInt("id_customer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idCustomer;
    }
}

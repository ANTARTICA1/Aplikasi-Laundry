package aplikasilaundry;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Transaksi {

public static DefaultTableModel getData() {
    DefaultTableModel model = new DefaultTableModel(
        new String[]{"ID Transaksi", "ID Customer", "Nama Customer", "Tanggal", "Berat", "Harga/Kg", "Total Harga", "Status"}, 0
    );

    try {
        Connection conn = Koneksi.getConnection();
        String sql = """
            SELECT t.id_transaksi, c.id_customer, c.nama, t.tanggal, t.berat, t.harga_per_kg, t.total_harga, t.status
            FROM transaksi t
            JOIN customer c ON t.id_customer = c.id_customer
        """;

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_transaksi"),
                rs.getInt("id_customer"), 
                rs.getString("nama"),
                rs.getString("tanggal"),
                rs.getDouble("berat"),
                rs.getInt("harga_per_kg"), 
                rs.getInt("total_harga"),
                rs.getString("status")
            });
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal load data!");
    }
    
    return model;
}


    public static void insert(int idCustomer, double berat, int hargaPerKg, int total, String status) {
        try {
        Connection conn = Koneksi.getConnection();
        String sql = "INSERT INTO transaksi (id_customer, tanggal, berat, harga_per_kg, total_harga, status) VALUES (?, CURDATE(), ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idCustomer);
        ps.setDouble(2, berat);
        ps.setInt(3, hargaPerKg); 
        ps.setInt(4, total);
        ps.setString(5, status);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal menambah data!");
    }
    }

    public static void update(int idTransaksi, double berat, int hargaPerKg, int total, String status) {
    try {
        Connection conn = Koneksi.getConnection();
        String sql = "UPDATE transaksi SET berat=?, harga_per_kg=?, total_harga=?, status=? WHERE id_transaksi=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, berat);       
        ps.setInt(2, hargaPerKg);     
        ps.setInt(3, total);          
        ps.setString(4, status);
        ps.setInt(5, idTransaksi);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal update data transaksi!");
    }
    }

    public static void delete(int idTransaksi) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "DELETE FROM transaksi WHERE id_transaksi=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTransaksi);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menghapus data!");
        }
    }

    public static int hitungTotal(double berat, int hargaPerKg) {
        return (int)(berat * hargaPerKg);
    }

    private static int getIdCustomer(Connection conn, String namaCustomer) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("SELECT id_customer FROM customer WHERE nama=?");
        pst.setString(1, namaCustomer);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) return rs.getInt("id_customer");
        return -1; 
    }

    public static int getIdCustomerByTransaksi(int idTransaksi) {
        int idCustomer = -1;
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT id_customer FROM transaksi WHERE id_transaksi=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTransaksi);
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

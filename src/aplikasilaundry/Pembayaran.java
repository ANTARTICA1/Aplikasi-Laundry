/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Pembayaran {

    public static DefaultTableModel getData() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID Pembayaran", "Nama Customer", "Tanggal Bayar", "Jumlah Bayar", "Status"}, 0
        );

        try {
            Connection conn = Koneksi.getConnection();
            String sql = """
                SELECT p.id_pembayaran, c.nama, p.tanggal_bayar, p.jumlah_bayar, p.status_pembayaran
                FROM pembayaran p
                JOIN transaksi t ON p.id_transaksi = t.id_transaksi
                JOIN customer c ON t.id_customer = c.id_customer
            """;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_pembayaran"),
                    rs.getString("nama"),
                    rs.getString("tanggal_bayar"),
                    rs.getInt("jumlah_bayar"),
                    rs.getString("status_pembayaran")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal load data pembayaran!");
        }

        return model;
    }

    public static void insert(int idTransaksi, int jumlahBayar, String status) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO pembayaran (id_transaksi, tanggal_bayar, jumlah_bayar, status_pembayaran) "
                       + "VALUES (?, CURDATE(), ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTransaksi);
            ps.setInt(2, jumlahBayar);
            ps.setString(3, status);
            ps.executeUpdate();

            // Jika lunas, update status transaksi
            if(status.equalsIgnoreCase("Lunas")) {
                String updateTransaksi = "UPDATE transaksi SET status='Selesai' WHERE id_transaksi=?";
                PreparedStatement ps2 = conn.prepareStatement(updateTransaksi);
                ps2.setInt(1, idTransaksi);
                ps2.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Pembayaran berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menambah pembayaran!");
        }
    }

    public static void update(int idPembayaran, int jumlahBayar, String status) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE pembayaran SET jumlah_bayar=?, status_pembayaran=? WHERE id_pembayaran=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, jumlahBayar);
            ps.setString(2, status);
            ps.setInt(3, idPembayaran);
            ps.executeUpdate();

            if(status.equalsIgnoreCase("Lunas")) {
                String updateTransaksi = "UPDATE transaksi t JOIN pembayaran p ON t.id_transaksi = p.id_transaksi "
                                       + "SET t.status='Selesai' WHERE p.id_pembayaran=?";
                PreparedStatement ps2 = conn.prepareStatement(updateTransaksi);
                ps2.setInt(1, idPembayaran);
                ps2.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Pembayaran berhasil diubah!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal update pembayaran!");
        }
    }

    public static void delete(int idPembayaran) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "DELETE FROM pembayaran WHERE id_pembayaran=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idPembayaran);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pembayaran berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal hapus pembayaran!");
        }
    }

}


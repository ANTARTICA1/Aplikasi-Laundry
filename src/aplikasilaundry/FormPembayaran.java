/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasilaundry;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author KRISNA
 */
public class FormPembayaran extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormPembayaran.class.getName());

    public FormPembayaran() {
        initComponents();
    Color biruIsi = new Color(0, 51, 102);      
    Color biruHeader = new Color(0, 34, 68);  
    tabelPembayaran.setBackground(biruIsi);
    tabelPembayaran.setForeground(Color.WHITE);
    tabelPembayaran.setSelectionBackground(new Color(0, 102, 204));
    tabelPembayaran.setSelectionForeground(Color.WHITE);
    tabelPembayaran.setFillsViewportHeight(true);
    jScrollPane1.getViewport().setBackground(biruIsi);
    DefaultTableCellRenderer header = new DefaultTableCellRenderer();
    header.setBackground(biruHeader);
    header.setForeground(Color.WHITE);
    header.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    header.setOpaque(true);
    tabelPembayaran.setShowGrid(true);
    tabelPembayaran.setGridColor(new Color(180, 180, 180));
    tabelPembayaran.setRowMargin(1);
    tabelPembayaran.getTableHeader().setDefaultRenderer(header);
        
    tabelPembayaran.setModel(Pembayaran.getData());
        
        loadComboTransaksi();
        loadStatusBayar();
        txtTotalHarga.setEditable(false);
    }
    
    private void loadStatusBayar() {
    cmbStatusBayar.removeAllItems();
    cmbStatusBayar.addItem("Belum Lunas");
    cmbStatusBayar.addItem("Lunas");
}   
    private void isiTotalHarga() {
    try {
        int row = tabelPembayaran.getSelectedRow();
        if (row == -1) return;

        row = tabelPembayaran.convertRowIndexToModel(row);

        int idPembayaran = Integer.parseInt(
            tabelPembayaran.getModel().getValueAt(row, 0).toString()
        );

        Connection conn = Koneksi.getConnection();

        String sql = """
            SELECT t.total_harga
            FROM pembayaran p
            JOIN transaksi t ON p.id_transaksi = t.id_transaksi
            WHERE p.id_pembayaran = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idPembayaran);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            txtTotalHarga.setText(
                String.valueOf(rs.getInt("total_harga"))
            );
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void tampilkanStruk(int idPembayaran) {
     try {
        Connection conn = Koneksi.getConnection();

        String sql = """
            SELECT
                p.id_pembayaran,
                t.id_transaksi,
                c.nama,
                t.berat,
                t.total_harga,
                t.status,
                p.jumlah_bayar,
                p.status_pembayaran,
                p.tanggal_bayar
            FROM pembayaran p
            JOIN transaksi t ON p.id_transaksi = t.id_transaksi
            JOIN customer c ON t.id_customer = c.id_customer
            WHERE p.id_pembayaran = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idPembayaran);

        ResultSet rs = ps.executeQuery();

        StringBuilder sb = new StringBuilder();

        if (rs.next()) {

sb.append("╔════════════════════════════════╗\n");
sb.append("║         STRUK LAUNDRY                  ║\n");
sb.append("╚════════════════════════════════╝\n\n");

sb.append(String.format("%-15s    : %s\n", "Nama", rs.getString("nama")));
sb.append(String.format("%-15s      : %.2f kg\n", "Berat", rs.getDouble("berat")));
sb.append(String.format("%-15s  : Rp %,d\n", "Total Harga", rs.getInt("total_harga")));
sb.append(String.format("%-15s : %s\n", "Status Cucian", rs.getString("status")));
sb.append(String.format("%-15s: Rp %,d\n", "Jumlah Bayar", rs.getInt("jumlah_bayar")));
sb.append(String.format("%-15s  : %s\n", "Status Bayar", rs.getString("status_pembayaran")));
sb.append(String.format("%-15s: %s\n", "Tanggal Bayar", rs.getString("tanggal_bayar")));

sb.append("╔════════════════════════════════╗\n");
sb.append("║ Terima kasih telah menggunakan   ║\n");
sb.append("║       jasa laundry kami                      ║\n");
sb.append("╚════════════════════════════════╝\n");



            txtStruk.setText(sb.toString());
        }

    } catch(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal menampilkan struk!");
    }
}
    private void loadComboTransaksi() {
    try {
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT t.id_transaksi, c.nama FROM transaksi t "
                   + "JOIN customer c ON t.id_customer = c.id_customer "
                   + "WHERE t.status <> 'Selesai'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        comboTransaksi.removeAllItems();
        while(rs.next()) {
            // Masukkan ID + Nama agar bisa dipilih
            comboTransaksi.addItem(rs.getInt("id_transaksi") + " - " + rs.getString("nama"));
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal load combo transaksi!");
    }
}

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboTransaksi = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtJumlahBayar = new javax.swing.JTextField();
        txtTotalHarga = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbStatusBayar = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPembayaran = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtStruk = new javax.swing.JTextArea();
        btnCetak = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Transaksi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 147, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Bayar");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Total Harga");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 182, -1));

        comboTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(comboTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 130, 41));

        jButton2.setText("Transaksi");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 160, 50));

        jButton3.setText("Keluar");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 70, 160, 50));

        txtJumlahBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahBayarActionPerformed(evt);
            }
        });
        jPanel1.add(txtJumlahBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 130, 41));

        txtTotalHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalHargaActionPerformed(evt);
            }
        });
        jPanel1.add(txtTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 130, 41));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Status Bayar");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, 203, -1));

        cmbStatusBayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbStatusBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 130, 41));

        jButton1.setText("Data Pelanggan");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 160, 50));

        btnTambah.setBackground(new java.awt.Color(51, 153, 255));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, 140, 50));

        btnUbah.setBackground(new java.awt.Color(255, 153, 0));
        btnUbah.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setText("Edit");
        btnUbah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUbahMouseClicked(evt);
            }
        });
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        jPanel1.add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 560, 140, 51));

        btnHapus.setBackground(new java.awt.Color(255, 51, 51));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 630, 140, 51));

        tabelPembayaran.setBackground(new java.awt.Color(153, 153, 153));
        tabelPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tabelPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPembayaran.setRowHeight(30);
        tabelPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPembayaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPembayaran);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, 540, 580));

        txtStruk.setColumns(20);
        txtStruk.setRows(5);
        jScrollPane2.setViewportView(txtStruk);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 190, 230, 380));

        btnCetak.setBackground(new java.awt.Color(51, 51, 51));
        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("Cetak Struk");
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCetakMouseClicked(evt);
            }
        });
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 660, 260, 70));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/bgPembayaran.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1530, 1000));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPembayaranMouseClicked

 int row = tabelPembayaran.getSelectedRow();
    if (row == -1) return;

    row = tabelPembayaran.convertRowIndexToModel(row);

    txtJumlahBayar.setText(
        tabelPembayaran.getModel().getValueAt(row, 3).toString()
    );

    cmbStatusBayar.setSelectedItem(
        tabelPembayaran.getModel().getValueAt(row, 4).toString()
    );

    isiTotalHarga();
    }//GEN-LAST:event_tabelPembayaranMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        // TODO add your handling code here:
          try {
        int idPembayaran = Integer.parseInt(tabelPembayaran.getValueAt(tabelPembayaran.getSelectedRow(), 0).toString());
        Pembayaran.delete(idPembayaran);
        tabelPembayaran.setModel(Pembayaran.getData());
        loadComboTransaksi();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
    }
    }//GEN-LAST:event_btnHapusMouseClicked

    private void btnUbahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbahMouseClicked
        // TODO add your handling code here:
         try {
        int idPembayaran = Integer.parseInt(tabelPembayaran.getValueAt(tabelPembayaran.getSelectedRow(), 0).toString());
        int jumlah = Integer.parseInt(txtJumlahBayar.getText());
        String status = cmbStatusBayar.getSelectedItem().toString();

        Pembayaran.update(idPembayaran, jumlah, status);
        tabelPembayaran.setModel(Pembayaran.getData());
        loadComboTransaksi();
    } catch(Exception e) {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah!");
    }
    }//GEN-LAST:event_btnUbahMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
 try {
        // transaksi WAJIB
        if (comboTransaksi.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi dulu!");
            return;
        }

        int idTransaksi = Integer.parseInt(
            comboTransaksi.getSelectedItem().toString().split(" - ")[0]
        );

        // jumlah BOLEH kosong
        int jumlah = 0;
        if (!txtJumlahBayar.getText().trim().isEmpty()) {
            jumlah = Integer.parseInt(txtJumlahBayar.getText());
        }

        // status BOLEH kosong
        String status = "BELUM BAYAR";
        if (cmbStatusBayar.getSelectedItem() != null) {
            status = cmbStatusBayar.getSelectedItem().toString();
        }

        Pembayaran.insert(idTransaksi, jumlah, status);
        tabelPembayaran.setModel(Pembayaran.getData());

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Jumlah bayar harus angka!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal menambah data!");
    }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnCetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCetakMouseClicked
        // TODO add your handling code here:
        
    int row = tabelPembayaran.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Pilih transaksi terlebih dahulu!");
        return;
    }

    row = tabelPembayaran.convertRowIndexToModel(row);

    int idPembayaran = Integer.parseInt(
        tabelPembayaran.getModel().getValueAt(row, 0).toString()
    );

    tampilkanStruk(idPembayaran);

    try {
        txtStruk.print();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal mencetak struk!");
    }
    }//GEN-LAST:event_btnCetakMouseClicked

    private void txtTotalHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHargaActionPerformed

    private void txtJumlahBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahBayarActionPerformed

    private void comboTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTransaksiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormPembayaran().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cmbStatusBayar;
    private javax.swing.JComboBox<String> comboTransaksi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelPembayaran;
    private javax.swing.JTextField txtJumlahBayar;
    private javax.swing.JTextArea txtStruk;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration//GEN-END:variables
}

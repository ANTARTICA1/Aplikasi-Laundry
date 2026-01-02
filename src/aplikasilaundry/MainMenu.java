/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasilaundry;

/**
 *
 * @author KRISNA
 */
public class MainMenu {
public static void main(String[] args) {

    int idTransaksi = 1; 

    Pembayaran.insert(idTransaksi, 25000, "Lunas");

    System.out.println("Pembayaran berhasil");
}

    
}

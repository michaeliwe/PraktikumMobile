package com.example.michaeliwe.michael_1202160197_si4004_pab_modul4;

public class menu {
    String imagePath,nama,harga,deskripsi;

    public menu(String imagePath, String nama, String harga, String deskripsi) {
        this.imagePath = imagePath;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}

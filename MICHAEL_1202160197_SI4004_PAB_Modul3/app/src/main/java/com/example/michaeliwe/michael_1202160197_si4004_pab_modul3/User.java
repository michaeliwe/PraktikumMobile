package com.example.michaeliwe.michael_1202160197_si4004_pab_modul3;

public class User {
    private String nama;
    private String title;
    private final int jk;

    public User(String nama, String title, int jk) {
        this.nama = nama;
        this.title = title;
        this.jk = jk;
    }

    public String getNama(){

        return nama;
    }

    public String getTitle(){

        return title;
    }

    public int getJk(){

        return jk;
    }
}

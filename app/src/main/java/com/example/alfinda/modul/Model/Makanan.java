package com.example.alfinda.modul.Model;

import com.google.gson.annotations.SerializedName;

public class Makanan {
    @SerializedName("idMakanan")
    private String idMakanan;
    @SerializedName("menu")
    private String menu;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("harga")
    private String harga;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("review")
    private String review;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("suka")
    private int suka;
    @SerializedName("komentar")
    private int komentar;
    @SerializedName("gambar")
    private String gambar;
    private String action;

    public Makanan(String idMakanan, String menu, String kategori, String harga, String alamat, String review, String tanggal, int suka, int komentar, String gambar, String
            action) {
        this.idMakanan = idMakanan;
        this.menu = menu;
        this.kategori = kategori;
        this.harga = harga;
        this.alamat = alamat;
        this.review = review;
        this.tanggal = tanggal;
        this.suka = suka;
        this.komentar = komentar;
        this.gambar = gambar;
        this.action = action;
    }

    public String getIdMakanan() {
        return idMakanan;
    }

    public void setIdMakanan(String idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getSuka() {
        return suka;
    }

    public void setSuka(int suka) {
        this.suka = suka;
    }

    public int getKomentar() {
        return komentar;
    }

    public void setKomentar(int komentar) {
        this.komentar = komentar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

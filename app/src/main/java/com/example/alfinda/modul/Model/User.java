package com.example.alfinda.modul.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("nama")
    private String nama;
    @SerializedName("jk")
    private String jk;
    @SerializedName("email")
    private String email;
    private String action;

    public User(String idUser, String username, String password, String nama, String jk, String email, String action) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.jk = jk;
        this.email = email;
        this.action = action;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAction() {
        return action;
    }

}

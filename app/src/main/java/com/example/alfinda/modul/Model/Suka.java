package com.example.alfinda.modul.Model;

import com.google.gson.annotations.SerializedName;

public class Suka {
    @SerializedName("idSuka")
    private String idSuka;
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("idMakanan")
    private String idMakanan;
    private String action;

    public Suka(String idSuka, String idUser, String idMakanan, String action) {
        this.idSuka = idSuka;
        this.idUser = idUser;
        this.idMakanan = idMakanan;
        this.action = action;
    }

    public String getIdSuka() {
        return idSuka;
    }

    public void setIdSuka(String idSuka) {
        this.idSuka = idSuka;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdMakanan() {
        return idMakanan;
    }

    public void setIdMakanan(String idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getAction() {
        return action;
    }

}

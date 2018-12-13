package com.example.alfinda.modul.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetMakanan {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Makanan> result = new ArrayList<Makanan>();
    @SerializedName("message")
    private String message;
    public GetMakanan() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Makanan> getResult() {
        return result;
    }

    public void setResult(List<Makanan> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

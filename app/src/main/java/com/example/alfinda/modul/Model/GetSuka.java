package com.example.alfinda.modul.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSuka {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Suka> result = new ArrayList<Suka>();
    @SerializedName("message")
    private String message;
    public GetSuka() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Suka> getResult() {
        return result;
    }

    public void setResult(List<Suka> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
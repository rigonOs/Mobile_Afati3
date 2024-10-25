package com.example.afati3;

public class UserDataModel {
    private String username;
    private String surname;
    private double pagaBruto;
    private double kontributiPensional;
    private double pagaTatueshme;

    public UserDataModel(String username, String surname, double pagaBruto, double kontributiPensional, double pagaTatueshme) {
        this.username = username;
        this.surname  = surname;
        this.pagaBruto = pagaBruto;
        this.kontributiPensional = kontributiPensional;
        this.pagaTatueshme = pagaTatueshme;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getPagaBruto() {
        return pagaBruto;
    }

    public void setPagaBruto(double pagaBruto) {
        this.pagaBruto = pagaBruto;
    }

    public double getKontributiPensional() {
        return kontributiPensional;
    }

    public void setKontributiPensional(double kontributiPensional) {
        this.kontributiPensional = kontributiPensional;
    }

    public double getPagaTatueshme() {
        return pagaTatueshme;
    }

    public void setPagaTatueshme(double pagaTatueshme) {
        this.pagaTatueshme = pagaTatueshme;
    }
}

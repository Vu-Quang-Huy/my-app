package com.example.vuquanghuy_b17dcat098_btl.model;

public class User {
    private String name,email,pass;
    private long coins=10;

    public User() {
    }

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }
}

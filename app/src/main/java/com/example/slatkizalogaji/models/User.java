package com.example.slatkizalogaji.models;

public class User {

    private String username;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String password;
    private String type;

    public User(String username, String name, String surname, String address, String phone, String password, String type) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

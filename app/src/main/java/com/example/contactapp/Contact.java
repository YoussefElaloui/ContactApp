package com.example.contactapp;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String gender;
    private String number;
    private int picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public Contact() {
    }

    public Contact(String name, String gender, String number, int picture) {
        this.name = name;
        this.gender = gender;
        this.number = number;
        this.picture = picture;
    }
}

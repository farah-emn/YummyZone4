package com.example.yummyzone.classes;

import com.google.firebase.database.DatabaseReference;

public class user {
    String email;
    String password;
    String firstName;
    String lastName;
    String mobileNumber;
    String city;
    String street;
    String district;
    String username;


    public user(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public user(String firstName, String lastName, String mobileNumber, String city, String street, String district) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.city = city;
        this.street = street;
        this.district = district;

    }

    public user(String username, String email, String password, String firstName, String lastName, String mobileNumber, String city, String street, String district) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.city = city;
        this.street = street;
        this.district = district;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

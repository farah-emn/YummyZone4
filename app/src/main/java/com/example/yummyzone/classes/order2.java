package com.example.yummyzone.classes;

import java.util.ArrayList;

public class order2 {
    ArrayList<order> items;
    String mobile;
    String restaurantName;
    String username;
    String date;
    String price;
    String city, street, district;
    String orderNumber;


    public order2(ArrayList<order> items, String mobile, String restaurantName, String username, String date, String price, String city, String street, String district, String orderNumber ) {
        this.items = items;
        this.mobile = mobile;
        this.restaurantName = restaurantName;
        this.username = username;
        this.date = date;
        this.price = price;
        this.city = city;
        this.street = street;
        this.district = district;
        this.orderNumber = orderNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<order> getItems() {
        return items;
    }

    public void setItems(ArrayList<order> items) {
        this.items = items;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}

package com.example.yummyzone.classes;

public class restaurantAccount {
    String restaurant_name, password, comNum, delivery_fee, delivery_time, mobilenumber, logo;

    public restaurantAccount(String resName, String password, String comNum, String delivery_fee, String delivery_time, String mobilenumber, String logo) {
        this.restaurant_name = resName;
        this.password = password;
        this.comNum = comNum;
        this.delivery_fee = delivery_fee;
        this.delivery_time = delivery_time;
        this.mobilenumber = mobilenumber;
        this.logo=logo;
    }

    public void setComNum(String comNum) {
        this.comNum = comNum;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getPassword() {
        return password;
    }

    public String getComNum() {
        return comNum;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public String getNumber() {
        return mobilenumber;
    }
}
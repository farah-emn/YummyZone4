package com.example.yummyzone.classes;

public class restaurantAccount {
    String restaurant_name, password, comNum, delivery_fee, delivery_time, mobilenumber, categories;

    public restaurantAccount(String resName, String password, String comNum, String fee, String time, String number, String categories) {
        this.restaurant_name = resName;
        this.password = password;
        this.comNum = comNum;
        this.delivery_fee = fee;
        this.delivery_time = time;
        this.mobilenumber = number;
        this.categories = categories;
    }

    public String getCategories() {
        return categories;
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

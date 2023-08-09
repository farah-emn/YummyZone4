package com.example.yummyzone.classes;

public class shipped {
    String mobile;
    String restaurantName;
    String date;
    String price;
    String img;

    public shipped(String mobile, String restaurantName, String date, String price) {
        this.mobile = mobile;
        this.restaurantName = restaurantName;
        this.date = date;
        this.price = price;
    }

    public shipped(String mobile, String restaurantName, String date, String price, String img) {
        this.mobile = mobile;
        this.restaurantName = restaurantName;
        this.date = date;
        this.price = price;
        this.img = img;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

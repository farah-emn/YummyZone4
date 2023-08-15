package com.example.yummyzone.classes;

public class shipped {
    String mobile;
    String restaurantName;
    String date;
    String price;
    String img;
    String city;
    String district;
    String street;
    String state;

    public shipped(String mobile, String restaurantName, String date, String price, String state) {
        this.mobile = mobile;
        this.restaurantName = restaurantName;
        this.date = date;
        this.price = price;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public shipped(String mobile, String restaurantName, String date, String price, String img, String city, String district, String street, String state) {
        this.mobile = mobile;
        this.restaurantName = restaurantName;
        this.date = date;
        this.price = price;
        this.img = img;
        this.city=city;
        this.district=district;
        this.street=street;
        this.state = state;
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

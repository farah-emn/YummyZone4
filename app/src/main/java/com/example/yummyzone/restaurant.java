package com.example.yummyzone;

public class restaurant {
    private int restaurant_image;
    private int like_image;
    private String restaurant_name;
    private String description;
    private String price;

    public restaurant(int restaurant_image, String restaurant_name, String description, String price) {
        this.restaurant_image = restaurant_image;
        this.restaurant_name = restaurant_name;
        this.description = description;
        this.price = price;
    }

    public int getRestaurant_image() {
        return restaurant_image;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setRestaurant_image(int restaurant_image) {
        this.restaurant_image = restaurant_image;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

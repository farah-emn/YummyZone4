package com.example.yummyzone.classes;

public class Cart {
    private String item_image;
    private String item_name;
    private String qty;
    private String item_price;
    private String restaurantid;
    private  String total_price;
    public Cart(){

    }
    public Cart(String item_image, String item_name, String qty, String item_price, String restaurantid,String total_price) {
        this.item_image = item_image;
        this.item_name = item_name;
        this.qty=qty;
        this.item_price = item_price;
        this.restaurantid=restaurantid;
        this.total_price=total_price;
    }

    public String getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(String restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }


    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

}
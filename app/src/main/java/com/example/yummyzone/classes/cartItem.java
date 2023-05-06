package com.example.yummyzone.classes;

public class cartItem {
    private int item_image;
    private String name;
    private String number;
    private String price;

    public cartItem(int item_image, String name, String number, String price) {
        this.item_image = item_image;
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public int getItem_image() {
        return item_image;
    }


    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPrice() {
        return price;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

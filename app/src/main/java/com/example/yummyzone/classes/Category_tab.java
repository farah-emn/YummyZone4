package com.example.yummyzone.classes;

public class Category_tab {
    private String name ,image;
    public Category_tab() {
    }

    public Category_tab(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
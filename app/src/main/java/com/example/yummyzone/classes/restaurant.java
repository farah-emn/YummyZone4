package com.example.yummyzone.classes;

public class restaurant {

    private String name,image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    restaurant(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public restaurant(String name,String image) {
        this.name = name;
        this.image=image;
    }
}

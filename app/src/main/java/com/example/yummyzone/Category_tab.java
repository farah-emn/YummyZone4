package com.example.yummyzone;

public class Category_tab {
    private String title;
    private int image;
    public Category_tab(String title,int image) {
        this.title = title;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
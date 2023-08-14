package com.example.yummyzone.classes;

public class orderItem {
    String name, num;

    public orderItem(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }
}

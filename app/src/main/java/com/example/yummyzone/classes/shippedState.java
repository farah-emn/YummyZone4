package com.example.yummyzone.classes;


public class shippedState {
    String orderNumber, date, price, address, mobileNumber, received, notReceived;



    public shippedState(String orderNumber, String date, String price, String address, String mobileNumber) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.price = price;
        this.address = address;
        this.mobileNumber = mobileNumber;

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getReceived() {
        return received;
    }

    public String getNotReceived() {
        return notReceived;
    }
}
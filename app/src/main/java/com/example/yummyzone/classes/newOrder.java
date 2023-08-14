package com.example.yummyzone.classes;

import android.widget.TextView;

public class newOrder {
    String orderNumber, date, price, address, mobileNumber, reject, seeDetails, ready;

    public newOrder(String orderNumber, String date, String price, String address, String mobileNumber) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.price = price;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public newOrder(String orderNumber, String date, String price, String address, String mobileNumber, String reject, String seeDetails, String ready) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.price = price;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.reject = reject;
        this.seeDetails = seeDetails;
        this.ready= ready;
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

    public String getReject() {
        return reject;
    }

    public String getSeeDetails() {
        return seeDetails;
    }
}

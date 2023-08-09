package com.example.yummyzone.classes;

public class order {
    String itemName;
    String ItemNumber;

    public order(String itemName, String itemNumber) {
        this.itemName = itemName;
        ItemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNumber() {
        return ItemNumber;
    }

    public void setItemNumber(String itemNumber) {
        ItemNumber = itemNumber;
    }
}

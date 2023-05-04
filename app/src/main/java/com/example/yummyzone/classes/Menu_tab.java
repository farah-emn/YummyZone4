package com.example.yummyzone.classes;

public class Menu_tab {
    private int item_img,icon_favorite;
    private String item_name,item_price,pre_time;

    public int getIcon_favorite() {
        return icon_favorite;
    }

    public void setIcon_favorite(int icon_favorite) {
        this.icon_favorite = icon_favorite;
    }

    public int getItem_img() {
        return item_img;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getPre_time() {
        return pre_time;
    }

    public void setPre_time(String pre_time) {
        this.pre_time = pre_time;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public void setItem_img(int item_img) {
        this.item_img = item_img;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Menu_tab(int item_img, int icon_favorite, String item_name, String item_price, String pre_time){
this.item_img=item_img;
this.icon_favorite=icon_favorite;
this.item_name=item_name;
this.item_price=item_price;
this.pre_time=pre_time;




  }
}

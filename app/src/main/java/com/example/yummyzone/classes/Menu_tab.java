package com.example.yummyzone.classes;

public class Menu_tab {

    private String item_name;
    private String item_price;
    private String preparation_time;
    private String item_image;
    private String restaurantid;
    private String cateid;
    private String calories;
    private String description;
    private String favorite_icon;


    public Menu_tab(String item_name, String item_image,String item_price,String preparation_time, String favorite_icon) {
        this.item_name=item_name;
        this.item_image=item_image;
        this.item_price=item_price;
        this.preparation_time=preparation_time;
        this.favorite_icon=favorite_icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu_tab(String key, String item_name, String item_image, String favorite_icon) {
    }



    private String icon;

    Menu_tab(){


    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCateid() {
        return cateid;
    }

    public String getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(String restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getFavorite_icon() {
        return favorite_icon;
    }

    public void setFavorite_icon(String favorite_icon) {
        this.favorite_icon = favorite_icon;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(String preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public Menu_tab(String item_name,String favorite_icon,String item_price,String preparation_time,String item_image,String restaurantid,String cateid,String calories){
this.item_name=item_name;
this.item_price=item_price;
this.preparation_time=preparation_time;
this.item_image=item_image;
this.cateid=cateid;
this.restaurantid=restaurantid;
this.calories=calories;
this.favorite_icon=favorite_icon;




  }
}

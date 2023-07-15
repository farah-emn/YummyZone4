package com.example.yummyzone.classes;

public class restaurant {

    private String restaurant_name,logo,delivery_fee,categoryBeverages_id,categoryPizza_id,categoryCoffee_id,categoryDesserts_id,categorySandwiche_id,categoryBurgers_id;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    restaurant(){

    }

    public String getCategoryBurgers_id() {
        return categoryBurgers_id;
    }

    public void setCategoryBurgers_id(String categoryBurgers_id) {
        this.categoryBurgers_id = categoryBurgers_id;
    }

    public String getCategorySandwiche_id() {
        return categorySandwiche_id;
    }

    public void setCategorySandwiche_id(String categorySandwiche_id) {
        this.categorySandwiche_id = categorySandwiche_id;
    }

    public String getCategoryPizza_id() {
        return categoryPizza_id;
    }

    public void setCategoryPizza_id(String categoryPizza_id) {
        this.categoryPizza_id = categoryPizza_id;
    }

    public String getCategoryDesserts_id() {
        return categoryDesserts_id;
    }

    public void setCategoryDesserts_id(String categoryDesserts_id) {
        this.categoryDesserts_id = categoryDesserts_id;
    }

    public String getCategoryCoffee_id() {
        return categoryCoffee_id;
    }

    public void setCategoryCoffee_id(String categoryCoffee_id) {
        this.categoryCoffee_id = categoryCoffee_id;
    }

    public String getCategoryBeverages_id() {
        return categoryBeverages_id;
    }

    public void setCategoryBeverages_id(String categoryBeverages_id) {
        this.categoryBeverages_id = categoryBeverages_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public restaurant(String restaurant_name, String logo,String delivery_fee,String categoryBeverages_id,String categoryPizza_id,String categoryCoffee_id,String categoryDesserts_id,String categorySandwiche_id,String categoryBurgers_id) {
        this.restaurant_name = restaurant_name;
        this.logo=logo;
        this.delivery_fee=delivery_fee;
        this.categorySandwiche_id=categorySandwiche_id;
        this.categoryBeverages_id=categoryBeverages_id;
        this.categoryCoffee_id=categoryCoffee_id;
        this.categoryDesserts_id=categoryDesserts_id;
        this.categoryPizza_id=categoryPizza_id;
        this.categoryBurgers_id=categoryBurgers_id;


    }
}

package com.example.yusuk.grush;

/**
 * Created by yusuk on 2017/12/18.
 * RestaurantList_List画面で使用するためのGetter/Setter
 */

public class RestaurantListModel {

    String restaurantName;
    String genre1;
    String genre2;
    String genre3;
    Integer customerMax;
    Integer customerMin;
    Integer numberOfTables;
    Integer discountRate;
    double distance;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setId(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getGenre() {
        // ジャンルを結合して返す
        return genre1 + "," + genre2 + "," + genre3;
    }

    public void setGenre(String genre1, String genre2, String genre3) {
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
    }

    public Integer getCustomerMax (){
        return customerMax;
    }

    public void setCustomerMax (Integer customerMax){
        this.customerMax = customerMax;
    }

    public Integer getCustomerMin (){
        return customerMin;
    }

    public void setCustomerMin (Integer customerMin){
        this.customerMin = customerMin;
    }

    public Integer getNumberOfTables (){
        return numberOfTables;
    }

    public void setNumberOfTables (Integer numberOfTables){
        this.numberOfTables = numberOfTables;
    }

    public Integer getDiscountRate (){
        return discountRate;
    }

    public void setDiscountRate (Integer discountRate){
        this.discountRate = discountRate;
    }

    public Double getDistance (){
        return distance;
    }

    public void setDistance (Integer distance){
        this.distance = distance;
    }

}

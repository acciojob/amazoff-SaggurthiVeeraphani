package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        this.deliveryTime = changeToInt(deliveryTime);

    }
    public int changeToInt(String s){

        String str = s.substring(0,2);
        String str1 = s.substring(2,4);
        int number = Integer.parseInt(str);
        int number1 = Integer.parseInt(str1);
        int ans = number*60 + number1;
        return ans;
    }

    public String getId() {

        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;}
}

package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    // order hashmap
    HashMap<String,Order> OrderDB = new HashMap<>();

    //DeliveryPartner hashmap
    HashMap<String,DeliveryPartner> deliveryPartnerDB = new HashMap<>();

    //Order-Partner Pair
    HashMap<String,String> OrderPartnerDB = new HashMap<>();

    //PartnerId List<OrderId>
    HashMap<String, List<String>> partnerOrderDB = new HashMap<>();





    public void addOrderINDB(Order order){

        OrderDB.put(order.getId(),order);
    }
    public void addpartnerINDB(String partnerId){
        DeliveryPartner obj = new DeliveryPartner(partnerId);
        deliveryPartnerDB.put(partnerId,obj);
    }
    public void orderpartnerINDB(String orderId,String partnerId){
        OrderPartnerDB.put(orderId,partnerId);
        List<String> list = partnerOrderDB.getOrDefault(partnerId,new ArrayList<>());
        list.add(orderId);
        partnerOrderDB.put(partnerId,list);
    }
    public Order getorderFromDB(String orderId){
        return OrderDB.get(orderId);
    }

    public DeliveryPartner getdeliverypartnerFromDb(String partnerId){

        return deliveryPartnerDB.get(partnerId);
    }

    public int getordercountfromDB(String partnerId){
        int count = 0;
        if(partnerOrderDB.containsKey(partnerId)){
            List<String> list = partnerOrderDB.get(partnerId);
            for(String s:list){
                count++;
            }
        }
        return count;
    }
    public List<String> getordersFromDB(String partnerId){
        List<String> list = new ArrayList<>();
        if(partnerOrderDB.containsKey(partnerId)) {
            list = partnerOrderDB.get(partnerId);
        }
        return list;
    }
    public List<String> getallordersFromDB(){
        List<String> allOrders = new ArrayList<>();
        for(String s : OrderDB.keySet()){
            allOrders.add(s);
        }
        return allOrders;
    }
    public int getunassignedFromDB(){
        int count = 0;
        for(String s: OrderDB.keySet()){
            if(OrderPartnerDB.containsKey(s)==false){
                count++;
            }
        }
        return count;
    }
    //








   //delete all partners
    public void deleteallPartner(String partnerid){
        List<String> orders = new ArrayList<>();
        if(partnerOrderDB.containsKey(partnerid)){
            orders = partnerOrderDB.get(partnerid);
            partnerOrderDB.remove(partnerid);
        }
        for(String s: orders){
            if(OrderPartnerDB.containsKey(s)){
                OrderPartnerDB.remove(s);
            }
        }
        if(deliveryPartnerDB.containsKey(partnerid)){
            deliveryPartnerDB.remove(partnerid);
        }

    }
    //remove orders
    public void removeorderINDB(String orderId){
        String s = null;
        if(OrderPartnerDB.containsKey(orderId)){
            s = s +OrderPartnerDB.get(orderId);
            OrderPartnerDB.remove(orderId);
        }
        if(partnerOrderDB.containsKey(s)){
            List<String> order = new ArrayList<>();
            order = partnerOrderDB.get(s);
            for(String str:order){
                if(str == orderId){
                    order.remove(str);
                }
            }
        }
        if(OrderDB.containsKey(orderId)){
            OrderDB.remove(orderId);
        }

    }




}

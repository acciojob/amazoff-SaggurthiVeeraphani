package com.driver;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazOff_service {
    @Autowired
    AmazOffrepository ar;
    public void addOrder(Order order){
        ar.addOrder(order);
    }
    public void addpartner(DeliveryPartner dp){
        ar.addPartner(dp);
    }
    public void assigning(String order_id,String partner_id){
        ar.assigning(order_id,partner_id);
    }
    public Order getOrder(String OrderId){
        return ar.getOrder(OrderId);
    }
    public DeliveryPartner getPartner(String partnerId){
        return ar.getPartner(partnerId);
    }
    public int order_count(String partnerId){
        return ar.orderCount(partnerId);
    }
    public List<String> listOrder(){
        return ar.listOfOrder();
    }
    public List<String> listbyorder(String partnerid){
        return ar.listbyorder(partnerid);
    }
    public int unassigned(){
        return ar.unassignOrder();
    }
    public int getorderleftfromtime(String time,String partnerid){
        return ar.getOrderLeftAfterGivenTime(time,partnerid);
    }
    public String gettime(String PartnerId){
        return ar.getLastDeliveryTime(PartnerId);
    }
    public void deleteByPartnerId(String partnerId){
        ar.deletepartnerbyid(partnerId);
    }
    public void deleteorder(String OrderId){
        ar.deleteorder(OrderId);
    }
}
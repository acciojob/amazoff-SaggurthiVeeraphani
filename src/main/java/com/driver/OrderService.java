package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderrepository;

    public void addorder(Order order){
        orderrepository.addOrderINDB(order);
    }
    public void addparter(String partnerId){
        orderrepository.addpartnerINDB(partnerId);
    }
    public void addorderpartner(String orderId,String partnerId){
        orderrepository.orderpartnerINDB(orderId,partnerId);
    }
    public Order getOrder(String orderId){
        Order order = orderrepository.getorderFromDB(orderId);
        return order;
    }
    public DeliveryPartner getPartner(String partnerId){
        DeliveryPartner deliverypartner = orderrepository.getdeliverypartnerFromDb(partnerId);
        return deliverypartner;
    }

    public int getordercount(String partnerId){
        int ans = orderrepository.getordercountfromDB(partnerId);
        return ans;
    }

    public List<String> getallorders(String partnerId){
        List<String> ans = orderrepository.getordersFromDB(partnerId);
        return ans;
    }
    public List<String> getallOrder(){
        List<String> order = orderrepository.getallordersFromDB();
        return order;
    }

    public int countOfunassigned(){
     int count = orderrepository.getunassignedFromDB();
     return count;
    }
















    public void deletepartner(String partnerId){
    orderrepository.deleteallPartner(partnerId);
    }
    public void deleteorder(String orderid){
        orderrepository.removeorderINDB(orderid);
    }

}

package com.driver;

import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class AmazOffrepository {

    HashMap<String,Order> orderdb = new HashMap<>();
    HashMap<String,DeliveryPartner> partner = new HashMap<>();
    HashMap<String,List<String>> order_partner = new HashMap<>();
    public void addOrder(Order order){
        orderdb.put(order.getId(),order);
    }
    public void addPartner(DeliveryPartner dp){
        partner.put(dp.getId(), dp);
    }
    public void assigning(String order_id,String partner_id){
        if(order_partner.containsKey(partner_id)){
            order_partner.get(partner_id).add(order_id);
        }
        else{
            List<String> ides = new ArrayList<>();
            ides.add(order_id);
            order_partner.put(partner_id,ides);
        }
        partner.get(partner_id).setNumberOfOrders(partner.get(partner_id).getNumberOfOrders()+1);
    }
    public Order getOrder(String order_id){
        return orderdb.getOrDefault(order_id,null);
    }
    public DeliveryPartner getPartner(String partner_Id){
        return partner.getOrDefault(partner_Id,null);
    }
    public int orderCount(String partnerId){
        int ans = partner.get(partnerId).getNumberOfOrders();
        return ans;
    }
    public List<String> listOfOrder(){
        List<String> ans = new ArrayList<>();
        for(String x:orderdb.keySet()){
            ans.add(x);
        }
        return ans;
    }
    public List<String> listbyorder(String partnerid){
        return order_partner.getOrDefault(partnerid,new ArrayList<String>());
    }
    public int unassignOrder(){
        int orders = orderdb.size();
        int count = 0;
        for(String s:order_partner.keySet()){
            count+=(order_partner.get(s).size());
        }
        return orders-count;
    }
    public int getOrderLeftAfterGivenTime(String time,String partnerId){
        int count = 0;
        int deliverytime = Integer.valueOf(time.substring(0,2))*60+Integer.valueOf(time.substring(3));
        List<String> ans = order_partner.get(partnerId);
        for(String s : ans){
            if(orderdb.get(s).getDeliveryTime()>deliverytime){
                count++;
            }
        }
        return count;
    }
    public String getLastDeliveryTime(String partnerId){
        int time = 0;
        List<String> ans = order_partner.get(partnerId);
        for(String s : ans){
            int t = orderdb.get(s).getDeliveryTime();
            time = Math.max(time,t);
        }
        int min = time%60;
        int hr = (time-min)/60;
        StringBuilder result = new StringBuilder();
        if(hr<10){
            result.append(0);
        }
        result.append(hr);
        result.append(':');
        if(min<10){
            result.append(0);
        }
        result.append(min);
        return String.valueOf(result);
    }
    public void deletepartnerbyid(String partnerId){
        partner.remove(partnerId);
        order_partner.remove(partnerId);
    }
    public void deleteorder(String OrderId){
        orderdb.remove(OrderId);
        for(String s : order_partner.keySet()){
            List<String> ans = order_partner.get(s);
            for(int i = 0;i<ans.size();i++){
                if(OrderId.equals(ans.get(i))){
                    ans.remove(i);
                    return;
                }
            }
        }
    }
}
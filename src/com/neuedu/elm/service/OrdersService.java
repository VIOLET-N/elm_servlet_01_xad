package com.neuedu.elm.service;

import com.neuedu.elm.entity.Orders;

import java.util.List;

public interface OrdersService {
    //下订单（先清空当前用户购物车中当前商家的所有食品，然后生成订单和订单明细，最后返回订单号）
    public int createOrders(String userId,Integer businessId,Integer daId);
    public Orders getOrdersById(Integer orderId);
    public List<Orders> listOrdersByUserId(String userId);
}

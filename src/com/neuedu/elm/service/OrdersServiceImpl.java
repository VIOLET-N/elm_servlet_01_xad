package com.neuedu.elm.service;

import com.neuedu.elm.dao.*;
import com.neuedu.elm.entity.Business;
import com.neuedu.elm.entity.Cart;
import com.neuedu.elm.entity.OrderDetail;
import com.neuedu.elm.entity.Orders;
import com.neuedu.elm.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    private BusinessDao businessDao = new BusinessDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    private OrdersDao ordersDao = new OrdersDaoImpl();
    private OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();

    @Override
    public int createOrders(String userId, Integer businessId, Integer daId) {
        int orderId = 0;
        try {
            DBUtil.beginTransaction();
            Cart cart = new Cart();
            cart.setBusinessId(businessId);
            cart.setUserId(userId);
            List<Cart> list = cartDao.listCart(cart);
            Business business = businessDao.getBusinessById(businessId);
            Double ordersTotal = 0.0;
            for (Cart c: list){
                ordersTotal += c.getFood().getFoodPrice() * c.getQuantity();
            }

            ordersTotal += business.getDeliveryPrice();

            Orders orders = new Orders();
            orders.setUserId(userId);
            orders.setBusinessId(businessId);
            orders.setOrderTotal(ordersTotal);
            orders.setDaId(daId);
            orderId = ordersDao.saveOrders(orders);


            List<OrderDetail> orderDetailList = new ArrayList<>();
            for (Cart c: list){
                OrderDetail od = new OrderDetail();
                od.setOrderId(orderId);
                od.setFoodId(c.getFoodId());
                od.setQuantity(c.getQuantity());
                orderDetailList.add(od);
            }
            orderDetailDao.saveOrderDetailBatch(orderDetailList);
            cartDao.removeCart(cart);
            DBUtil.commitTransaction();
        } catch (Exception e) {
            orderId = 0;
            try {
                DBUtil.rollbackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return orderId;
    }

    @Override
    public Orders getOrdersById(Integer orderId) {
        Orders orders = null;
        try {
            DBUtil.getConnection();
            orders = ordersDao.getOrdersById(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }

        return orders;
    }

    @Override
    public List<Orders> listOrdersByUserId(String userId) {
        List<Orders> list = new ArrayList<>();
        try {
            DBUtil.beginTransaction();
//            DBUtil.getConnection();
            list = ordersDao.listOrdersById(userId);
            for (Orders o: list){
                List<OrderDetail> odList = orderDetailDao.listOrderDetailByOrderId(o.getOrderId());
                o.setOdList(odList);
            }
            DBUtil.commitTransaction();
        } catch (Exception e) {
            try {
                DBUtil.rollbackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }
}

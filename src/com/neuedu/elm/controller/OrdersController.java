package com.neuedu.elm.controller;

import com.neuedu.elm.service.OrdersService;
import com.neuedu.elm.service.OrdersServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrdersController {
    private OrdersService service = new OrdersServiceImpl();

    public Object createOrders(HttpServletRequest request, HttpServletResponse response){
        String userId = request.getParameter("userId");
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        return service.createOrders(userId, businessId, daId);
    }

    public Object getOrdersById(HttpServletRequest request, HttpServletResponse response){
        Integer orderId = Integer.valueOf(request.getParameter("ordersId"));
        return service.getOrdersById(orderId);
    }

    public Object listOrdersByUserId(HttpServletRequest request, HttpServletResponse response){
        String userId = request.getParameter("userId");
        return service.listOrdersByUserId(userId);
    }
}

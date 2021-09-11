package com.neuedu.elm.controller;

import com.neuedu.elm.service.OrderDetailService;
import com.neuedu.elm.service.OrderDetailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderDetailController {
    private OrderDetailService service = new OrderDetailServiceImpl();

    public Object listOrderDetailByOrderId(HttpServletRequest request, HttpServletResponse response){
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        return service.listOrderDetailByOrderId(orderId);
    }
}

package com.neuedu.elm.service;

import com.neuedu.elm.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    public List<OrderDetail> listOrderDetailByOrderId(Integer orderId);
}

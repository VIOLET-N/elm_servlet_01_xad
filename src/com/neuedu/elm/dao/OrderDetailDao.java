package com.neuedu.elm.dao;

import com.neuedu.elm.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDao {
    public int saveOrderDetailBatch(List<OrderDetail> list) throws Exception;
    public List<OrderDetail> listOrderDetailByOrderId(Integer orderId) throws Exception;
}

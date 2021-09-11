package com.neuedu.elm.service;

import com.neuedu.elm.dao.OrderDetailDao;
import com.neuedu.elm.dao.OrderDetailDaoImpl;
import com.neuedu.elm.entity.OrderDetail;
import com.neuedu.elm.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailDao dao = new OrderDetailDaoImpl();

    @Override
    public List<OrderDetail> listOrderDetailByOrderId(Integer orderId) {
        List<OrderDetail> list = new ArrayList<>();
        try {
            DBUtil.getConnection();
            list = dao.listOrderDetailByOrderId(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }
}

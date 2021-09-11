package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Business;
import com.neuedu.elm.entity.Orders;
import com.neuedu.elm.utils.DBUtil;
import com.neuedu.elm.utils.DateFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    @Override
    public int saveOrders(Orders orders) throws Exception {
        int i = 0;
        String sql = "insert into orders values (null,?,?,?,?,?,0)";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, orders.getUserId());
            statement.setInt(2, orders.getBusinessId());
            statement.setString(3, DateFormat.getDate());
            statement.setDouble(4, orders.getOrderTotal());
            statement.setInt(5, orders.getDaId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                i = resultSet.getInt(1);
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return i;
    }

    @Override
    public Orders getOrdersById(Integer orderId) throws Exception {
        Orders orders = null;
        StringBuffer sql = new StringBuffer();
        sql.append(" select o.*,");
        sql.append(" b.business_id b_business_id, ");
        sql.append(" b.business_name b_business_name, ");
        sql.append(" b.delivery_price b_delivery_price ");
        sql.append(" from orders o left join elm_business b on o.business_id=b.business_id ");
        sql.append(" where o.order_id=?");
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql.toString());
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                orders = new Orders();
                orders.setOrderId(resultSet.getInt("order_id"));
                orders.setUserId(resultSet.getString("user_id"));
                orders.setBusinessId(resultSet.getInt("business_id"));
                orders.setOrderDate(resultSet.getString("order_date"));
                orders.setOrderTotal(resultSet.getDouble("order_total"));
                orders.setOrderState(resultSet.getInt("order_state"));
                Business business = new Business();
                business.setBusinessId(resultSet.getInt("b_business_id"));
                business.setBusinessName(resultSet.getString("b_business_name"));
                business.setDeliveryPrice(resultSet.getDouble("b_delivery_price"));
                orders.setBusiness(business);
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return orders;
    }

    @Override
    public List<Orders> listOrdersById(String userId) throws Exception {
        List<Orders> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append(" select o.*, ");
        sql.append(" b.business_id b_business_id, ");
        sql.append(" b.business_name b_business_name, ");
        sql.append(" b.delivery_price b_delivery_price ");
        sql.append(" from orders o left join elm_business b on o.business_id=b.business_id ");
        sql.append(" where o.user_id=?");
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql.toString());
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Orders o = new Orders();
                o.setOrderId(resultSet.getInt("order_id"));
                o.setUserId(resultSet.getString("user_id"));
                o.setBusinessId(resultSet.getInt("business_id"));
                o.setOrderDate(resultSet.getString("order_date"));
                o.setOrderTotal(resultSet.getDouble("order_total"));
                o.setDaId(resultSet.getInt("da_id"));
                o.setOrderState(resultSet.getInt("order_state"));
                Business business = new Business();
                business.setBusinessId(resultSet.getInt("b_business_id"));
                business.setBusinessName(resultSet.getString("b_business_name"));
                business.setDeliveryPrice(resultSet.getDouble("b_delivery_price"));
                o.setBusiness(business);
                list.add(o);
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return list;
    }
}

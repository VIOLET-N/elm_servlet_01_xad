package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Food;
import com.neuedu.elm.entity.OrderDetail;
import com.neuedu.elm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    @Override
    public int saveOrderDetailBatch(List<OrderDetail> list) throws Exception {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer("insert into elm_order_detailet(order_id, food_id, quantity) values ");
        for (OrderDetail od: list){
            stringBuffer.append("(")
                    .append(od.getOrderId()).append(",")
                    .append(od.getFoodId()).append(",").
                    append(od.getQuantity()).append("),");
        }
        String sql = stringBuffer.toString().substring(0, stringBuffer.toString().length()-1);
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(statement);
        }
        return i;
    }

    @Override
    public List<OrderDetail> listOrderDetailByOrderId(Integer orderId) throws Exception {
        List<OrderDetail> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select o.*,");
        sql.append("f.food_id f_food_id,");
        sql.append("f.food_name f_food_name,");
        sql.append("f.food_price f_food_price");
        sql.append(" from elm_order_detailet o left join elm_food f on o.food_id=f.food_id ");
        sql.append(" where o.order_id=?");
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql.toString());
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                OrderDetail od = new OrderDetail();
                od.setOrderId(resultSet.getInt("od_id"));
                od.setOrderId(resultSet.getInt("order_id"));
                od.setFoodId(resultSet.getInt("food_id"));
                od.setQuantity(resultSet.getInt("quantity"));
                Food food = new Food();
                food.setFoodId(resultSet.getInt("f_food_id"));
                food.setFoodName(resultSet.getString("f_food_name"));
                food.setFoodPrice(resultSet.getDouble("f_food_price"));
                od.setFood(food);
                list.add(od);
            }
        }finally {
            DBUtil.close(statement);
        }
        return list;
    }
}

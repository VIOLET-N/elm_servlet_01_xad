package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Business;
import com.neuedu.elm.entity.Cart;
import com.neuedu.elm.entity.Food;
import com.neuedu.elm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Cart> listCart(Cart cart) throws Exception {
//        System.out.println(cart);
        List<Cart> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select elm_cart.*,");
        sql.append("elm_food.food_id f_food_id,");
        sql.append("elm_food.food_name f_food_name,");
        sql.append("elm_food.food_explain f_food_explain,");
        sql.append("elm_food.food_img f_food_img,");
        sql.append("elm_food.food_price f_food_price,");
        sql.append("elm_food.business_id f_business_id,");
        sql.append("elm_food.remarks f_remarks,");
        sql.append("elm_business.business_id b_business_id,");
        sql.append("elm_business.business_name b_business_name,");
        sql.append("elm_business.business_address b_business_address,");
        sql.append("elm_business.business_explain b_business_explain,");
        sql.append("elm_business.business_img b_business_img,");
        sql.append("elm_business.order_type_id b_order_type_id,");
        sql.append("elm_business.star_price b_star_price,");
        sql.append("elm_business.delivery_price b_delivery_price");
        sql.append(" from (elm_cart left join elm_food on elm_cart.food_id=elm_food.food_id) ");
        sql.append(" left join elm_business on elm_cart.business_id=elm_business.business_id ");
        sql.append(" where 1=1 ");
        if (cart.getBusinessId() != null){
            sql.append(" and elm_cart.business_id=").append(cart.getBusinessId());
        }
        if (cart.getUserId() != null){
            sql.append(" and elm_cart.user_id='").append(cart.getUserId()).append("'");
        }
//        System.out.println("sql |-> " + sql.toString());
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql.toString());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Cart c = new Cart();
                c.setCartId(resultSet.getInt("cart_id"));
                c.setFoodId(resultSet.getInt("food_id"));
                c.setBusinessId(resultSet.getInt("business_id"));
                c.setUserId(resultSet.getString("user_id"));
                c.setQuantity(resultSet.getInt("quantity"));
                Food f = new Food();
                f.setFoodId(resultSet.getInt("f_food_id"));
                f.setFoodName(resultSet.getString("f_food_name"));
                f.setFoodExplain(resultSet.getString("f_food_explain"));
                f.setFoodImg(resultSet.getString("f_food_img"));
                f.setFoodPrice(resultSet.getDouble("f_food_price"));
                f.setBusinessId(resultSet.getInt("f_business_id"));
                f.setRemarks(resultSet.getString("f_remarks"));
                c.setFood(f);
                Business b = new Business();
                b.setBusinessId(resultSet.getInt("b_business_id"));
                b.setBusinessName(resultSet.getString("b_business_name"));
                b.setBusinessAddress(resultSet.getString("b_business_address"));
                b.setBusinessExplain(resultSet.getString("b_business_explain"));
                b.setBusinessImg(resultSet.getString("b_business_img"));
                b.setOrderTypeId(resultSet.getInt("b_order_type_id"));
                b.setStarPrice(resultSet.getDouble("b_star_price"));
                b.setDeliveryPrice(resultSet.getDouble("b_delivery_price"));
                c.setBusiness(b);
                list.add(c);
            }

        }finally {
            DBUtil.close(resultSet, statement);
        }
        return list;
    }

    @Override
    public int saveCart(Cart cart) throws Exception {
        int i = 0;
        String sql = "insert into elm_cart values (null,?,?,?,1)";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cart.getFoodId());
            statement.setInt(2, cart.getBusinessId());
            statement.setString(3, cart.getUserId());
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(statement);
        }
        return i;
    }

    @Override
    public int removeCart(Cart cart) throws Exception {
        int i = 0;
        StringBuffer sql = new StringBuffer("delete from elm_cart where 1=1");
        if (cart.getFoodId() != null){
            sql.append(" and food_id=").append(cart.getFoodId());
        }
        if (cart.getBusinessId() != null){
            sql.append(" and business_id=").append(cart.getBusinessId());
        }
        if (cart.getUserId() != null){
            sql.append(" and user_id='").append(cart.getUserId()).append("'");
        }
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql.toString());
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(statement);
        }
        return i;
    }

    @Override
    public int updateCart(Cart cart) throws Exception {
        int i = 0;
        String sql = "update elm_cart set quantity = ? where food_id=? and user_id=?;";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, cart.getQuantity());
            statement.setInt(2, cart.getFoodId());
            statement.setString(3, cart.getUserId());
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(statement);
        }
        return i;
    }
}

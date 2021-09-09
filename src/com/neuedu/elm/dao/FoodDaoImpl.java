package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Food;
import com.neuedu.elm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) throws Exception {
        List<Food> list = new ArrayList<>();
        String sql = "select * from elm_food where business_id=?";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, businessId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Food food = new Food();
                food.setFoodId(resultSet.getInt("food_id"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setFoodExplain(resultSet.getString("food_explain"));
                food.setFoodImg(resultSet.getString("food_img"));
                food.setFoodPrice(resultSet.getDouble("food_price"));
                food.setBusinessId(resultSet.getInt("business_id"));
                food.setRemarks(resultSet.getString("remarks"));
                list.add(food);
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return list;
    }
}

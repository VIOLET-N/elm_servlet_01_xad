package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Business;
import com.neuedu.elm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception {
        List<Business> list = new ArrayList<>();
        String sql = "select * from elm_business where order_type_id=? order by business_id";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderTypeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Business business = new Business();
                business.setBusinessId(resultSet.getInt("business_id"));
                business.setBusinessName(resultSet.getString("business_name"));
                business.setBusinessAddress(resultSet.getString("business_address"));
                business.setBusinessExplain(resultSet.getString("business_explain"));
                business.setBusinessImg(resultSet.getString("business_img"));
                business.setOrderTypeId(resultSet.getInt("order_type_id"));
                business.setStarPrice(resultSet.getDouble("star_price"));
                business.setDeliveryPrice(resultSet.getDouble("delivery_price"));
                business.setRemarks(resultSet.getString("remarks"));
                list.add(business);
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return list;
    }

    @Override
    public Business getBusinessById(Integer id) throws Exception {
        Business business = null;
        String sql = "select * from elm_business where business_id=?";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                business = new Business();
                business.setBusinessId(resultSet.getInt("business_id"));
                business.setBusinessName(resultSet.getString("business_name"));
                business.setBusinessAddress(resultSet.getString("business_address"));
                business.setBusinessExplain(resultSet.getString("business_explain"));
                business.setBusinessImg(resultSet.getString("business_img"));
                business.setOrderTypeId(resultSet.getInt("order_type_id"));
                business.setStarPrice(resultSet.getDouble("star_price"));
                business.setDeliveryPrice(resultSet.getDouble("delivery_price"));
                business.setRemarks(resultSet.getString("remarks"));
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return business;
    }
}

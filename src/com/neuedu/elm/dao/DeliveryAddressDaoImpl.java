package com.neuedu.elm.dao;

import com.neuedu.elm.entity.DeliveryAddress;
import com.neuedu.elm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception {
        List<DeliveryAddress> list = new ArrayList<>();
        String sql = "select * from elm_delivery_address where user_id=? order by da_id";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                deliveryAddress.setDaId(resultSet.getInt("da_id"));
                deliveryAddress.setContactName(resultSet.getString("contact_name"));
                deliveryAddress.setContactSex(resultSet.getInt("contact_sex"));
                deliveryAddress.setContactTel(resultSet.getString("contact_tel"));
                deliveryAddress.setAddress(resultSet.getString("address"));
                deliveryAddress.setUserId(resultSet.getString("user_id"));
                list.add(deliveryAddress);
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return list;
    }

    @Override
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
        int i = 0;
        String sql = "insert into elm_delivery_address values (null ,?,?,?,?,?)";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, deliveryAddress.getContactName());
            statement.setInt(2, deliveryAddress.getContactSex());
            statement.setString(3, deliveryAddress.getContactTel());
            statement.setString(4, deliveryAddress.getAddress());
            statement.setString(5, deliveryAddress.getUserId());
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(statement);
        }
        return i;
    }

    @Override
    public int removeDeliveryAddress(Integer daId) throws Exception {
        int i = 0;
        String sql = "delete from elm_delivery_address where da_id=?";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, daId);
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(statement);
        }
        return i;
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) throws Exception {
        DeliveryAddress deliveryAddress = null;
        String sql = "select * from elm_delivery_address where da_id=?";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, daId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                deliveryAddress = new DeliveryAddress();
                deliveryAddress.setDaId(resultSet.getInt("da_id"));
                deliveryAddress.setContactName(resultSet.getString("contact_name"));
                deliveryAddress.setContactSex(resultSet.getInt("contact_sex"));
                deliveryAddress.setContactTel(resultSet.getString("contact_tel"));
                deliveryAddress.setAddress(resultSet.getString("address"));
                deliveryAddress.setUserId(resultSet.getString("user_id"));
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return deliveryAddress;
    }

    @Override
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
        int i = 0;
        String sql = "update elm_delivery_address set contact_name=?,contact_sex=?,contact_tel=?," +
                "address=?,user_id=? where da_id=?";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, deliveryAddress.getContactName());
            statement.setInt(2, deliveryAddress.getContactSex());
            statement.setString(3, deliveryAddress.getContactTel());
            statement.setString(4, deliveryAddress.getAddress());
            statement.setString(5, deliveryAddress.getUserId());
            statement.setInt(6, deliveryAddress.getDaId());
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(statement);
        }
        return i;
    }
}

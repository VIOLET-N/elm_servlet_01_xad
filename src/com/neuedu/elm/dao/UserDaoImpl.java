package com.neuedu.elm.dao;

import com.neuedu.elm.entity.User;
import com.neuedu.elm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    @Override
    public int saveUser(User u) throws Exception {
        int i = 0;
        String sql = "insert into elm_user(user_id, password, user_name, user_sex, user_img, del_tag) values (?,?,?,?,?,1)";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, u.getUserId());
            statement.setString(2, u.getPassword());
            statement.setString(3, u.getUserName());
            statement.setInt(4, u.getUserSex());
            statement.setString(5, u.getUserImg());
            i = statement.executeUpdate();
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return i;
    }

    @Override
    public User getUserByIdByPass(String id, String pass) throws Exception {
        User user = null;
        String sql = "select * from elm_user where user_id=? and password=?";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserSex(resultSet.getInt("user_sex"));
                user.setUserImg(resultSet.getString("user_img"));
                user.setDelTag(resultSet.getInt("del_tag"));
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return user;
    }

    @Override
    public User getUserById(String id) throws Exception {
        User user = null;
        String sql = "select * from elm_user where user_id=?";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getString("user_id"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserSex(resultSet.getInt("user_sex"));
                user.setUserImg(resultSet.getString("user_img"));
                user.setDelTag(resultSet.getInt("del_tag"));
            }
        }finally {
            DBUtil.close(resultSet, statement);
        }
        return user;
    }
}

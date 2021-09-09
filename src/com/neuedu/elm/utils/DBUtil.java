package com.neuedu.elm.utils;

import java.sql.*;

public class DBUtil {
    // 使用 ThreadLocal 保证线程安全
    private static final ThreadLocal<Connection> TL = new ThreadLocal<>();
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1/elm2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private static Connection createConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 获取链接对象
    public static Connection getConnection(){
        Connection connection = TL.get();
        if (connection == null){
            connection = createConnection();
            TL.set(connection);
        }
        return connection;
    }

    // 开启事务
    public static void beginTransaction() throws SQLException {
        Connection connection = TL.get();
        if (connection == null){
            connection = createConnection();
            TL.set(connection);
        }
        connection.setAutoCommit(false);
    }

    // 提交事务
    public static void commitTransaction() throws SQLException {
        Connection connection = TL.get();
        connection.commit();
    }

    // 回滚事务
    public static void rollbackTransaction() throws SQLException {
        Connection connection = TL.get();
        connection.rollback();
    }

    // 释放语句对象和结果集
    public static void close(ResultSet resultSet, PreparedStatement statement){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement statement){
        close(null, statement);
    }
    // 释放连接对象
    public static void close(){
        Connection connection = TL.get();
        try {
            if (connection != null){
                connection.close();
                TL.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

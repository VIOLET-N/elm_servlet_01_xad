package com.neuedu.elm.dao;

import com.neuedu.elm.entity.User;

// dao层不处理异常，抛给service层处理，实现事务
public interface UserDao {
    public int saveUser(User u) throws Exception;
    public User getUserByIdByPass(String id, String pass) throws Exception;
    public User getUserById(String id) throws Exception;
}

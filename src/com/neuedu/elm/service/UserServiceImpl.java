package com.neuedu.elm.service;

import com.neuedu.elm.dao.UserDao;
import com.neuedu.elm.dao.UserDaoImpl;
import com.neuedu.elm.entity.User;
import com.neuedu.elm.utils.DBUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public int saveUser(User u) {
        int i = 0;
        try {
            DBUtil.getConnection();
            i = dao.saveUser(u);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return i;
    }

    @Override
    public User getUserByIdByPass(String id, String pass) {
        User user = null;
        try {
            DBUtil.getConnection();
            user = dao.getUserByIdByPass(id, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return user;
    }

    @Override
    public User getUserById(String id) {
        User user = null;
        try {
            DBUtil.getConnection();
            user = dao.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return user;
    }
}

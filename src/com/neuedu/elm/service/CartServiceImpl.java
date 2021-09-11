package com.neuedu.elm.service;

import com.neuedu.elm.dao.CartDao;
import com.neuedu.elm.dao.CartDaoImpl;
import com.neuedu.elm.entity.Cart;
import com.neuedu.elm.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao dao = new CartDaoImpl();

    @Override
    public List<Cart> listCart(Cart cart) {
        List<Cart> list = new ArrayList<>();
        try {
            DBUtil.getConnection();
            list = dao.listCart(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }

    @Override
    public int saveCart(Cart cart) {
        int i = 0;
        try {
            DBUtil.getConnection();
            i = dao.saveCart(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return i;
    }

    @Override
    public int removeCart(Cart cart) {
        int i = 0;
        try {
            DBUtil.getConnection();
            i = dao.removeCart(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return i;
    }

    @Override
    public int updateCart(Cart cart) {
        int i = 0;
        try {
            DBUtil.getConnection();
            i = dao.updateCart(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return i;
    }
}

package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Cart;

import java.util.List;

public interface CartDao {
    public List<Cart> listCart(Cart cart) throws Exception;
    public int saveCart(Cart cart) throws Exception;
    public int removeCart(Cart cart) throws Exception;
    public int updateCart(Cart cart) throws Exception;
}

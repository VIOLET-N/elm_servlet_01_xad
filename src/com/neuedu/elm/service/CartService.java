package com.neuedu.elm.service;

import com.neuedu.elm.entity.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> listCart(Cart cart);
    public int saveCart(Cart cart);
    public int removeCart(Cart cart);
    public int updateCart(Cart cart);
}

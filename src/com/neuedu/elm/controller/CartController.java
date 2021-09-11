package com.neuedu.elm.controller;

import com.neuedu.elm.entity.Cart;
import com.neuedu.elm.service.CartService;
import com.neuedu.elm.service.CartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartController {
    private CartService service = new CartServiceImpl();

    public Object listCart(HttpServletRequest request, HttpServletResponse response){
        Cart cart = new Cart();
        if (request.getParameter("businessId") != null && !request.getParameter("businessId").equals("")){
            cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        }
        String userId = request.getParameter("userId");
        if (userId != null && !userId.equals("")){
            cart.setUserId(userId);
        }
        return service.listCart(cart);
    }

    public Object saveCart(HttpServletRequest request, HttpServletResponse response){
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        return service.saveCart(cart);
    }

    public Object removeCart(HttpServletRequest request, HttpServletResponse response){
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));

        return service.removeCart(cart);
    }

    public Object updateCart(HttpServletRequest request, HttpServletResponse response){
        Cart cart = new Cart();
        cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
        cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
        cart.setUserId(request.getParameter("userId"));
        cart.setQuantity(Integer.valueOf(request.getParameter("quantity")));
        return service.updateCart(cart);
    }
}

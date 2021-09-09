package com.neuedu.elm.controller;

import com.neuedu.elm.entity.Food;
import com.neuedu.elm.service.FoodService;
import com.neuedu.elm.service.FoodServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FoodController {
    public Object listFoodByBusinessId(HttpServletRequest request, HttpServletResponse response){
        Integer businessId = Integer.parseInt(request.getParameter("businessId"));
        FoodService service = new FoodServiceImpl();
        return service.listFoodByBusinessId(businessId);
    }
}

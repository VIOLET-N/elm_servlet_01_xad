package com.neuedu.elm.service;

import com.neuedu.elm.entity.Food;

import java.util.List;

public interface FoodService {
    public List<Food> listFoodByBusinessId(Integer businessId);
}

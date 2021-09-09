package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Food;

import java.util.List;

public interface FoodDao {
    public List<Food> listFoodByBusinessId(Integer businessId) throws Exception;
}

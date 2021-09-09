package com.neuedu.elm.service;

import com.neuedu.elm.dao.FoodDao;
import com.neuedu.elm.dao.FoodDaoImpl;
import com.neuedu.elm.entity.Food;
import com.neuedu.elm.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {
    private FoodDao dao = new FoodDaoImpl();
    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        List<Food> list = new ArrayList<>();
        try {
            DBUtil.getConnection();
            list = dao.listFoodByBusinessId(businessId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }
}

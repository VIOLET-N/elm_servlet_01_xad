package com.neuedu.elm.dao;

import com.neuedu.elm.entity.Business;

import java.util.List;

public interface BusinessDao {
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception;
    public Business getBusinessById(Integer id) throws Exception;
    public List<Business> listBusinessByFoodOrName(String foodOrName) throws Exception;
}

package com.neuedu.elm.service;

import com.neuedu.elm.dao.BusinessDao;
import com.neuedu.elm.dao.BusinessDaoImpl;
import com.neuedu.elm.entity.Business;
import com.neuedu.elm.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class BusinessServiceImpl implements BusinessService {
    private BusinessDao dao = new BusinessDaoImpl();

    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
        List<Business> list = new ArrayList<>();
        try {
            DBUtil.getConnection();
            list = dao.listBusinessByOrderTypeId(orderTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }

    @Override
    public Business getBusinessById(Integer id) {
        Business business = null;
        try {
            DBUtil.getConnection();
            business = dao.getBusinessById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return business;
    }

    @Override
    public List<Business> listBusinessByFoodOrName(String foodOrName) {
        List<Business> list = new ArrayList<>();
        try {
            DBUtil.getConnection();
            list = dao.listBusinessByFoodOrName(foodOrName);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }
}

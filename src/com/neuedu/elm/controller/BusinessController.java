package com.neuedu.elm.controller;

import com.neuedu.elm.entity.Business;
import com.neuedu.elm.service.BusinessService;
import com.neuedu.elm.service.BusinessServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusinessController {
    BusinessService service = new BusinessServiceImpl();

    public Object listBusinessByOrderTypeId(HttpServletRequest request, HttpServletResponse response){
        String orderTypeId = request.getParameter("orderTypeId");
        int typeId = 0;
        if (orderTypeId != null && !orderTypeId.equals("")){
            typeId = Integer.parseInt(orderTypeId);
        }
        return service.listBusinessByOrderTypeId(typeId);
    }

    public Object getBusinessById(HttpServletRequest request, HttpServletResponse response){
        String businessId = request.getParameter("businessId");
        int id = 0;
        if (businessId != null && !businessId.equals("")){
            id = Integer.parseInt(businessId);
        }
        return service.getBusinessById(id);
    }

    public Object listBusinessByFoodOrName(HttpServletRequest request, HttpServletResponse response){
        String foodOrName = request.getParameter("foodOrName");
        return service.listBusinessByFoodOrName(foodOrName);
    }
}

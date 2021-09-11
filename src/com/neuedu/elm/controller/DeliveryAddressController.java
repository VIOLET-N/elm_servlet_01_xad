package com.neuedu.elm.controller;

import com.neuedu.elm.entity.DeliveryAddress;
import com.neuedu.elm.service.DeliveryAddressService;
import com.neuedu.elm.service.DeliveryAddressServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliveryAddressController {
    private DeliveryAddressService service = new DeliveryAddressServiceImpl();

    public Object listDeliveryAddressByUserId(HttpServletRequest request, HttpServletResponse response){
        String userId = request.getParameter("userId");
        return service.listDeliveryAddressByUserId(userId);
    }

    public Object saveDeliveryAddress(HttpServletRequest request, HttpServletResponse response){
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setContactName(request.getParameter("contactName")); // 食品编号
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex"))); // 联系人性别
        deliveryAddress.setContactTel(request.getParameter("contactTel")); // 联系人电话
        deliveryAddress.setAddress(request.getParameter("address")); // 送货地址
        deliveryAddress.setUserId(request.getParameter("userId")); // 所素用户编号
        return service.saveDeliveryAddress(deliveryAddress);
    }

    public Object removeDeliveryAddress(HttpServletRequest request, HttpServletResponse response){
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        return service.removeDeliveryAddress(daId);
    }

    public Object getDeliveryAddressById(HttpServletRequest request, HttpServletResponse response){
        Integer daId = Integer.valueOf(request.getParameter("daId"));
        return service.getDeliveryAddressById(daId);
    }

    public Object updateDeliveryAddress(HttpServletRequest request, HttpServletResponse response){
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setDaId(Integer.valueOf(request.getParameter("daId")));
        deliveryAddress.setContactName(request.getParameter("contactName"));
        deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
        deliveryAddress.setContactTel(request.getParameter("contactTel"));
        deliveryAddress.setAddress(request.getParameter("address"));
        deliveryAddress.setUserId(request.getParameter("userId"));
        return service.updateDeliveryAddress(deliveryAddress);
    }
}

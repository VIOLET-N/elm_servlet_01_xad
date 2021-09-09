package com.neuedu.elm.service;

import com.neuedu.elm.entity.Business;

import java.util.List;

public interface BusinessService {
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
    public Business getBusinessById(Integer id);
}

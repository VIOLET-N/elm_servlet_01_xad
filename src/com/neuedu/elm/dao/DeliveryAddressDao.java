package com.neuedu.elm.dao;

import com.neuedu.elm.entity.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressDao {
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception;
}

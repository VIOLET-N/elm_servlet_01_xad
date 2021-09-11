package com.neuedu.elm.service;

import com.neuedu.elm.dao.DeliveryAddressDao;
import com.neuedu.elm.dao.DeliveryAddressDaoImpl;
import com.neuedu.elm.entity.DeliveryAddress;
import com.neuedu.elm.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private DeliveryAddressDao dao = new DeliveryAddressDaoImpl();

    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
        List<DeliveryAddress> list = new ArrayList<>();
        try {
            DBUtil.getConnection();
            list = dao.listDeliveryAddressByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }

    @Override
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        int i = 0;
        try {
            DBUtil.getConnection();
            i = dao.saveDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return i;
    }

    @Override
    public int removeDeliveryAddress(Integer daId) {
        int i = 0;
        try {
            DBUtil.getConnection();
            i = dao.removeDeliveryAddress(daId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return i;
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) {
        DeliveryAddress deliveryAddress = null;
        try {
            DBUtil.getConnection();
            deliveryAddress = dao.getDeliveryAddressById(daId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return deliveryAddress;
    }

    @Override
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        int i = 0;
        try {
            DBUtil.getConnection();
            i = dao.updateDeliveryAddress(deliveryAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return i;
    }
}

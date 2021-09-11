package com.neuedu.elm.entity;

public class OrderDetail {
    private Integer orderId;
    private Integer foodId;
    private Integer quantity;
    private Food food;

    public OrderDetail(){}

    public OrderDetail(Integer orderId, Integer foodId, Integer quantity, Food food) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.food = food;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId=" + orderId +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                ", food=" + food +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}

package com.neuedu.elm.entity;

public class Cart {
    private Integer cartId;
    private Integer foodId;
    private Integer businessId;
    private String userId;
    private Integer quantity;
    private Food food = new Food();
    private Business business = new Business();

    public Cart(){}

    public Cart(Integer cartId, Integer foodId, Integer businessId, String userId,
                Integer quantity, Food food, Business business) {
        this.cartId = cartId;
        this.foodId = foodId;
        this.businessId = businessId;
        this.userId = userId;
        this.quantity = quantity;
        this.food = food;
        this.business = business;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", foodId=" + foodId +
                ", businessId=" + businessId +
                ", userId='" + userId + '\'' +
                ", quantity=" + quantity +
                ", food=" + food +
                ", business=" + business +
                '}';
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}

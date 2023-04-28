package com.zfh.jdbc1111.TestSpringBoot111.model;

public class OrderResponse {
    private boolean success;
    private Order order;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

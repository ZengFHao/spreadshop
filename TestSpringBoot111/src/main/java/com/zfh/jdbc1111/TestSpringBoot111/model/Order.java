package com.zfh.jdbc1111.TestSpringBoot111.model;
// http://localhost:8080/order?username=spreadzhao&goods_id=5&number=2;
public class Order {

    private String username;
    private int goods_id;
    private int num;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

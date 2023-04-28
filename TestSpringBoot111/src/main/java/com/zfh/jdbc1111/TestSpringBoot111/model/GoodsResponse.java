package com.zfh.jdbc1111.TestSpringBoot111.model;

import java.util.List;

public class GoodsResponse {
    private boolean success;

    private List<Goods> goods;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }


}

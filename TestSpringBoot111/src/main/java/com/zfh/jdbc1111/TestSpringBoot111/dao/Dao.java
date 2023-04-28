package com.zfh.jdbc1111.TestSpringBoot111.dao;


import com.zfh.jdbc1111.TestSpringBoot111.model.Category;
import com.zfh.jdbc1111.TestSpringBoot111.model.Goods;
import com.zfh.jdbc1111.TestSpringBoot111.model.LoginResult;
import com.zfh.jdbc1111.TestSpringBoot111.model.Modify;

import java.util.List;

public interface Dao {
    //查询
    List<Goods> find_goods(String sql);

    LoginResult find_account(String sql);

    public void update(String sql);

    public int find_Intdetail(String sql);

    public double find_Doudetail(String sql);

    Modify find_account_modify(String sql);

    List<Category> find_category(String sql);

}

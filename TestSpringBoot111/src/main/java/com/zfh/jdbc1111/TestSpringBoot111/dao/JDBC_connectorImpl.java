package com.zfh.jdbc1111.TestSpringBoot111.dao;


import com.zfh.jdbc1111.TestSpringBoot111.dao.BaseDao;
import com.zfh.jdbc1111.TestSpringBoot111.dao.Dao;
import com.zfh.jdbc1111.TestSpringBoot111.model.Category;
import com.zfh.jdbc1111.TestSpringBoot111.model.Goods;
import com.zfh.jdbc1111.TestSpringBoot111.model.LoginResult;
import com.zfh.jdbc1111.TestSpringBoot111.model.Modify;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC_connectorImpl extends BaseDao implements Dao {

    public List<Goods> find_goods(String sql){
        //System.out.println("start find");
        //String sql="select * from goods";
        /**
         * 开始的时候绑定的是params
         */
        ResultSet rs=query(sql,null);
        List<Goods> list = new ArrayList<>();
            try {
                while (rs.next()) {
                    Goods goods=new Goods();
                    goods.setGoods_id(rs.getInt(1));
                    goods.setGoods_name(rs.getString(2));
                    goods.setGoods_category(rs.getString(3));
                    goods.setGoods_storage(rs.getInt(4));
                    goods.setGoods_price(rs.getInt(5));
                    list.add(goods);
                }
//                boolean wrong = !rs.next();
//                System.out.println("!rs.next(): " + wrong);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                closeAll();
            }

        return list;
    }
    public  LoginResult  find_account(String sql){
        ResultSet rs=query(sql,null);
        LoginResult LR=new LoginResult();//默认所有参数都是null;
        LR.setUsername("default");
            try {
                while (rs.next()) {

                    LR.setUsername(rs.getString(1));
                    LR.setPasswd(rs.getString(2));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                closeAll();
            }
            return LR;

    }
    public void update(String sql){
        doPs(sql,null);
    }

    public int find_Intdetail(String sql){
        ResultSet rs=query(sql,null);
        int res=0;
        try {
            while (rs.next()){
                res=rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll();
        }
        return res;
    }

    public double find_Doudetail(String sql){
        ResultSet rs=query(sql,null);
        double res=0;
        try {
            while (rs.next()){
                res=rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll();
        }
        return res;
    }

    public Modify find_account_modify(String sql) {
        ResultSet rs=query(sql,null);
        Modify mod=new Modify();//默认所有参数都是null;
        mod.setUsername("default");
        try {
            while (rs.next()) {

                mod.setUsername(rs.getString(1));
                mod.setPassword(rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            closeAll();
        }
        return mod;
    }

    public  List<Category> find_category(String sql) {
        ResultSet rs=query(sql,null);
        List<Category> list=new ArrayList<>();

            try {
                while(rs.next()){
                    Category category=new Category();
                    category.setCategory(rs.getString(1));
                    list.add(category);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                closeAll();
            }
        return  list;
    }
}

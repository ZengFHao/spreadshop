package com.zfh.jdbc1111.TestSpringBoot111.model;

//实体类
public class Goods {
    private  int goods_id;
    private  String goods_name;
    private  String goods_category;
    private  int  goods_storage;
    private  int goods_price;


    public  int getGoods_id()
    {
        return goods_id;
    }

    public  void setGoods_id(int id){
        this.goods_id=id;
    }

    public  String getGoods_name(){
        return goods_name;
    }


    public  void setGoods_name(String name){
        this.goods_name=name;
    }
    public  String getGoods_category(){
        return  goods_category;
    }
    public void setGoods_category(String category){
        this.goods_category=category;
    }

    public int getGoods_storage(){
        return goods_storage;
    }
    public void setGoods_storage(int storage){
        goods_storage=storage;
    }
    public int getGoods_price(){
        return goods_price;
    }
    public void setGoods_price(int price){
        goods_price=price;
    }
}

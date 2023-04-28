package com.zfh.jdbc1111.TestSpringBoot111.controller;

import com.zfh.jdbc1111.TestSpringBoot111.dao.Dao;
import com.zfh.jdbc1111.TestSpringBoot111.dao.JDBC_connectorImpl;
import com.zfh.jdbc1111.TestSpringBoot111.model.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@EnableAutoConfiguration
public class GetController {
// http://localhost:8080/searchgoods?cmd=showall
    @RequestMapping({"/demo"})
    //@PathVariable需要使用restful风格，即参数更在路径后面，但我们现在使用的传统风格，即参数和路径之间用？间隔开
    //public String test(@PathVariable("show") String query) {
    public GoodsResponse test1(@RequestParam("show") String query) {
        //public  List<Goods> test(){
        Dao dao = new JDBC_connectorImpl();
//        List<Goods> list=null ;
        GoodsResponse response = new GoodsResponse();
        if (query.equals("showAll")) {
            String sql = "select * from goods";
//        list = dao.find_goods(sql);
            response.setGoods(dao.find_goods(sql));
            if (response.getGoods().isEmpty()) {
                response.setSuccess(false);
            } else {
                response.setSuccess(true);
//                return response;
            }
        }
//        }else{
//            String sql="select goods_name  from goods";
//            list=dao.find_goods(sql);
//        }
        return response;
    }
    //http://localhost:8080/login?username=spreadzhao&password=1234;
    @RequestMapping({"/login"})
    public LoginResult test2(@RequestParam Map<String, LoginResult> params){
        LoginResult res;
        String sql="select * from account where account_name='" + params.get("username")+"'";
        //select * from account where account_name='username';
        Dao dao = new JDBC_connectorImpl();
        res = dao.find_account(sql);
        res.setSuccess(false);
        if(!(res.getUsername().equals(params.get("username")))){//res.getUsername()==null;
            res.setMessage("Unknown username");
        }else if(!(res.getPasswd().equals(params.get("password")))){
            res.setMessage("Wrong password for user [" + params.get("username") + "]");
        }else{
            res.setSuccess(true);
            res.setMessage("Login Success!!");
        }
        //code=500;服务端内部有问题
        return res;
//        boolean user_flag=false;
//        boolean passwd_flag=false;
//        for(LoginResult Lr:list){
//            if(Lr.getUsername().equals(params.get("username"))) {
//                if(Lr.getPasswd().equals(params.get("password"))){
//                    user_flag=true;
//                    passwd_flag=true;
//                    ret = Lr;
//                    ret.setSuccess(true);
//                    ret.setMessage("Login Success");
//                }
//                else user_flag=true;
//                ret=Lr;
//                ret.setMessage("Wrong password");
//                ret.setSuccess(false);
//            }
//        }

//        if(!user_flag){
//            return params.get("username")+"username不存在";
//        } else if (user_flag && !passwd_flag) {
//            return params.get("password")+"password错误";
//
//        }else{
//            return "success";
//        }



    }
    // http://localhost:8080/register?username=zfh&password=666
    @RequestMapping({"/register"})
    public RegistResponse register(@RequestParam Map<String, String> params) {
        Dao dao = new JDBC_connectorImpl();
        Register Rs=new Register();
        RegistResponse Rr=new RegistResponse();
        Rr.setSuccess(false);
        Rs.setUsername(params.get("username"));
        Rs.setPassword(params.get("password"));
        String sql = "select * from account where account_name='" + params.get("username") + "'";
        LoginResult rs;
        String rst = null;
        rs = dao.find_account(sql);
        if (!(rs.getUsername().equals("default"))) {
            Rs.setMessage("Unseccess!!username:" + params.get("username") + " is existed!!!");
        } else {
            if (params.get("username").length() > 15) Rs.setMessage("Unseccess!!username too long more than 15");
            else if (params.get("password").length() > 15) Rs.setMessage("Unseccess!!password too long more than 15");
            else {
                //insert into account values ('zfh','134',0);
                sql = "insert into account values ('" + params.get("username") + "','" + params.get("password") + "',0)";
                dao.update(sql);
                Rs.setMessage("Regist success!!!");
                Rr.setSuccess(true);
                }
            }
        Rr.setRegister(Rs);
            return Rr;
        }

    // http://localhost:8080/order?username=spreadzhao&goods_id=5&number=2;
    @RequestMapping({"order"})
    public OrderResponse order(@RequestParam Map<String,String> params){
        Dao dao=new JDBC_connectorImpl();
        Order order=new Order();
        order.setUsername(params.get("username"));
        order.setGoods_id(Integer.parseInt(params.get("goods_id")));
        order.setNum(Integer.parseInt(params.get("number")));
        OrderResponse OR=new OrderResponse();
        OR.setSuccess(false);
        int storage;
        int price;
        double ballance;
        String sql="select goods_storage from goods where goods_id="+params.get("goods_id");
        storage=dao.find_Intdetail(sql);
        if(storage<Integer.parseInt(params.get("number"))){
            order.setMessage("The item is out of stock");
        }else{
            sql="select goods_price from goods where goods_id="+params.get("goods_id");
            price=dao.find_Intdetail(sql);
            sql="select ballance from account where account_name='"+params.get("username")+"'";
            ballance=dao.find_Doudetail(sql);
            if(price*Integer.parseInt(params.get("number"))>ballance){
                order.setMessage("Insufficient balance");
            }else {
                //update account set ballance =400 where account_name ='zfh';
                sql="update account set ballance="+(ballance-price*Integer.parseInt(params.get("number")))+" where account_name='"+params.get("username")+"'";
                dao.update(sql);
                //update goods set goods_storage=99 where goods_id=5;
                /**
                 * 在此处一直报错是因为定义的Map params中的values是string，而database中的values是int所以会导致错误
                 * 所以用Integer.parseInt()将String转化为int
                 */
                sql="update goods set goods_storage="+(storage-Integer.parseInt(params.get("number")))+" where goods_id="+Integer.parseInt(params.get("goods_id"));
                dao.update(sql);
                sql="select count(*) from orders";

                /**
                 * sql 在使用count查询所有元组时*得用一个括号括起来
                 */

                int orderNum=dao.find_Intdetail(sql)+1;
                LocalDateTime now = LocalDateTime.now();
                /**
                 * LocalDateTime now 返回的时间精度是纳秒，如果直接把这个时间存入database的话就会报错
                 * 因为database中的时间只给了十五个字符长度
                 * 所以需要将时间精度用subString()截取一下
                 * 但是还是出错，因为提前使用now.toString().substring(0,16);
                 * 然后在使用的时候仍然使用的now，仍然调用了系统默认的精确到纳秒的时间
                 */

                sql="insert into orders values("+orderNum+","+params.get("goods_id")+",'"
                        +now.toString().substring(0,19)+"','"+params.get("username")+"',"+Integer.parseInt(params.get("number"))+")";
                dao.update(sql);
                order.setMessage("The order was successful");
                OR.setSuccess(true);
                //res=sql;
            }
        }
        OR.setOrder(order);
        return OR;
    }

    // http://localhost:8080/searchgoods?cmd=showall
    @RequestMapping({"/searchgoods"})
    //@PathVariable需要使用restful风格，即参数更在路径后面，但我们现在使用的传统风格，即参数和路径之间用？间隔开
    //public String test(@PathVariable("show") String query) {
    public GoodsResponse test4(@RequestParam("cmd") String query) {
        Dao dao = new JDBC_connectorImpl();
        String sql;
//        List<Goods> list=null ;
        GoodsResponse response = new GoodsResponse();
        if(query.contains("category")){
            String queryNew=query.substring(8);
            sql="select * from goods where goods_category='"+queryNew+"'";
            response.setGoods(dao.find_goods(sql));
            if(response.getGoods().isEmpty()){
                response.setSuccess(false);
            }else {
                response.setSuccess(true);
            }
        } else if (query.contains("search")) {
            String queryNew=query.substring(6);
            sql="select * from goods where goods_name like'%"+queryNew+"%'";
            //s=queryNew;
            response.setGoods(dao.find_goods(sql));
            if(response.getGoods().isEmpty()){
                response.setSuccess(false);
            }else {
                response.setSuccess(true);
            }
        }else {
            if (query.equals("showall")) {
                sql = "select * from goods";
//        list = dao.find_goods(sql);
                response.setGoods(dao.find_goods(sql));
                if (response.getGoods().isEmpty()) {
                    response.setSuccess(false);
                } else {
                    response.setSuccess(true);
//                return response;
                }
            }else if(query.equals("getrecommand")){
                /**
                 * 如果想要在mysql中实现随机查询，可以在sql语句末尾添加
                 * order by rand() limit x
                 * 其中的X就是随机输出x个结果
                 *
                 */
                sql="select * from goods order by rand() limit 10";
                response.setGoods(dao.find_goods(sql));
                if (response.getGoods().isEmpty()) {
                    response.setSuccess(false);
                } else {
                    response.setSuccess(true);
//                return response;
                }
            }
        }

        //return s;
        return response;
    }
//http:localhost:8080/modify?username=zfh&password=123
    @RequestMapping({"/modify"})
    public ModifyResponse modify(@RequestParam Map<String,String> params){
        Dao dao=new JDBC_connectorImpl();
        ModifyResponse MR =new ModifyResponse();
        Modify mod =new Modify();
        Modify temp=new Modify();
        mod.setUsername(params.get("username"));
        mod.setPassword(params.get("password"));
        MR.setSuccess(false);
        String sql="select account_name , account_pwd from account where account_name='"+params.get("username")+"'";
        temp=dao.find_account_modify(sql);
        if(!(temp.getUsername().equals("default"))){
            if(!(temp.getPassword().equals(params.get("password")))){
                sql="update account set account_pwd='"+params.get("password")+"' where account_name='"+params.get("username")+"'";
                dao.update(sql);
                mod.setMessage("Modify Success!!!");
                MR.setSuccess(true);
            }else{
                mod.setMessage("The old and new passwords are consistent!!");
            }
        }else{
            mod.setMessage("No this username!!");
        }
        MR.setModify(mod);
        return MR;

    }

    //http:localhost:8080/searchcategory?cmd=getallcategory
    @RequestMapping({"/searchcategory"})
    public CategoryResponse searchcategory(@RequestParam("cmd") String query){
        Dao dao=new JDBC_connectorImpl();
        CategoryResponse CR=new CategoryResponse();
        String sql;
        List<Category> list=null;
        if (query.equals("getallcategory")){
            sql="select distinct goods_category from goods";
            list=dao.find_category(sql);
            CR.setSuccess(true);
        }else{
            CR.setSuccess(false);
        }
        CR.setCategories(list);
        return CR;

    }

}


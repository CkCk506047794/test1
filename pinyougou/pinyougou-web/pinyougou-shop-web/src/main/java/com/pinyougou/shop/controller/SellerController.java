package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Seller;
import com.pinyougou.service.SellerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//1  注解
@RestController
@RequestMapping("/seller")
public class SellerController{

//   3  提供方法后在调用SellerService
    @Reference(timeout = 10000)
    private  SellerService  sellerService;
//2  定义方法接收参数
    @PostMapping("/save")
    public boolean save(@RequestBody Seller seller){
        try {
            //调用上面sellerService的方法
            sellerService.save(seller);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

return false;
    }
}






////接受响应数据类型JSON
//@RestController
//@RequestMapping("/seller")
//public class SellerController {
////    注入商家服务接口代理对象
//    @Reference(timeout = 10000)
//    private SellerService sellerService;
////    添加商家 提供方法
//@PostMapping("/save")
////接收页面上的请求参数
//    public boolean save(@RequestBody Seller seller){
//        try {
//            sellerService.save(seller);
//         return  true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//return false;
//
//    }
//
//}

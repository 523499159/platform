package com.beizhi.cloud.front.services;

import com.beizhi.cloud.front.models.Product;
import com.beizhi.cloud.front.models.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by eric on 16/11/7.
 */
@Service
public class ProductService {
    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    FeignUserService feignUserService;

    final String SERVICE_NAME="service-b";

    @HystrixCommand(fallbackMethod = "fallbackSearchAll")
    public List<Product> readProductInfo() {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/products", List.class);
        //return feignUserService.readUserInfo();
    }
    private List<Product> fallbackSearchAll() {
        System.out.println("HystrixCommand fallbackMethod handle!");
        List<Product> ls = new ArrayList<Product>();
        Product product = new Product();
        product.setName("TestProductHystrixCommand");
        product.setCreatetime(new Date());
        ls.add(product);
        return ls;
    }
}

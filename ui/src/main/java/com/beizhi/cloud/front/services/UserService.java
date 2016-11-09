package com.beizhi.cloud.front.services;

import com.beizhi.cloud.front.models.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 16/11/7.
 */
@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    FeignUserService feignUserService;

    final String SERVICE_NAME="service-a";

    @HystrixCommand(fallbackMethod = "fallbackSearchAll")
    public List<User> readUserInfos() {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/user", List.class);
        //return feignUserService.readUserInfo();
    }

    private List<User> fallbackSearchAll() {
        System.out.println("HystrixCommand fallbackMethod handle!");
        List<User> ls = new ArrayList<User>();
        User user = new User();
        user.setUsername("TestHystrixCommand");
        ls.add(user);
        return ls;
    }

    public User saveUser(User user) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/user/"+user, User.class);

        //return feignUserService.readUserInfo();
    }

    public User updateUser(User user) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/user/"+user, User.class);

        //return feignUserService.readUserInfo();
    }
    public User deleteUser(Long id) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/user/"+id, User.class);

        //return feignUserService.readUserInfo();
    }
    public User getUser(Long id ) {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/user/"+id, User.class);

        //return feignUserService.readUserInfo();
    }

}

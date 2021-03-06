package com.beizhi.cloud.ms.service.a.test.web;

import com.beizhi.cloud.ms.servicea.domain.TUser;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kanghong.zhao on 2016-11-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    private int port = 8001;
    private String servicePathPre = "/user";
    private RestTemplate restTemplate = new RestTemplate();

//    private Integer userId;
    private TUser user;

    @Before
    public void before() {
        user = new TUser();
        user.setUserName("edison");
        user.setNameCn("冠希");
        user.setNameEn("Edison");
        user.setPassword("12345");
    }

    private TUser createUser(){
        String servicePath = servicePathPre;
        String url = "http://localhost:" + port + servicePath;

        TUser createReq = user;
        TUser userinfo = postJson(url, createReq, TUser.class, restTemplate);
        return userinfo;
    }

    @Test
    public void test_成功创建用户() {
        TUser userinfo = createUser();
//        user.setId(userinfo.getId());
//
//        assertNotNull(userinfo.getId());
        assertEquals(user.getUserName(), userinfo.getUserName());
    }

    @Test
    public void test_成功查询用户() {
        user = createUser();
//        assertNotNull(user.getId());
//
//        String servicePath = servicePathPre + "/" + user.getId();
//        String url = "http://localhost:" + port + servicePath;
//
//        TUser userinfo = restTemplate.getForObject(url, User.class);

//        assertEquals(user.getUserName(), userinfo.getUserName());
    }

    @Test
    public void test_成功删除用户() {
        user = createUser();
//        assertNotNull(user.getId());
//
//        String servicePath = servicePathPre + "/{id}";
//        String url = "http://localhost:" + port + servicePath;
//
//        restTemplate.delete(url, user.getId());
    }

    @Test
    public void test_成功更新用户() {
        user = createUser();
//        assertNotNull(user.getId());

        user.setNameEn("hahaha");
        String servicePath = servicePathPre + "/update";
        String url = "http://localhost:" + port + servicePath;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = new Gson().toJson(user);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);

//        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

        ResponseEntity<TUser> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, TUser.class);
        assertEquals(user.getNameEn(), resp.getBody().getNameEn());
    }

    public static <T, E> T postJson(String url,  E entity, Class<T> responseType, RestTemplate restTemplate){
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request=new HttpEntity(entity, headers);
        return restTemplate.postForObject(url, request, responseType);
    }

    public static <T> T postForObject(String url, Map<String, String> params, Class<T> responseType, RestTemplate restTemplate){
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();

        for(Map.Entry<String, String> entry : params.entrySet()){
            valueMap.add(entry.getKey(), entry.getValue());
        }

        if(restTemplate == null)
            restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, valueMap, responseType);
    }

}

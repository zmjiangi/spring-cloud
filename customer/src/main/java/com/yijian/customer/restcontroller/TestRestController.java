package com.yijian.customer.restcontroller;

import com.yijian.api.model.User;
import com.yijian.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zmjiangi on 2018/5/28.
 */
@RestController
public class TestRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类

    @RequestMapping(value = "/api/test/sayHello")
    public String sayHello() {
        String str = userService.sayHello("name");
        return str;
    }

    @RequestMapping("/api/test/getUser")
    public User getUser() {
        User user = userService.getUser("name", "password");
        if (null != user) {
            System.out.println(user.getName() + "," + user.getPassword());
        }
        return user;
    }

    @RequestMapping("/api/test/postUser")
    public User postUser() {
        User user = new User("name", "password");
        User postUser = userService.getByPost(user);
        if (null != postUser) {
            System.out.println(postUser.getName() + "," + postUser.getPassword());
        }
        return user;
    }

    /**
     * 用 restTemplate 来调用服务
     *
     * @return
     */
    @RequestMapping("/api/test/rest")
    public String restTest() {
        String providerMsg = restTemplate.getForEntity(
                "http://PROVIDER-SERVICE/svc/user/get?name=name&password=password",
                String.class
        ).getBody();

        return providerMsg;
    }

    @RequestMapping("/api/test/restPost")
    public User restTestPost() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        User user = new User("name", "password");
        ResponseEntity<User> response = restTemplate.postForEntity(
                "http://PROVIDER-SERVICE/svc/user/post",
                user,
                User.class
        );
        System.out.println(response.getStatusCode());
        return response.getBody();
    }

}

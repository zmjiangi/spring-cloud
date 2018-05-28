package com.yijian.api.service;

import com.yijian.api.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zmjiangi on 2018/5/28.
 */
@FeignClient(name = "provider-service")
public interface UserService {

    @GetMapping(value = "/svc/user/getName")
    String getName(@RequestParam("name") String name);

    @GetMapping(value = "/svc/user/get")
    User getUser(@RequestParam("name") String name, @RequestParam("password") String password);

    @PostMapping(value = "/svc/user/post")
    User getByPost(@RequestBody User user);

}

package com.yijian.api.fallback;

import com.yijian.api.model.User;
import com.yijian.api.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zmjiangi on 2018/5/28.
 */
@Component
public class UserServiceFallback implements UserService {

    @Override
    public String getName(@RequestParam("name") String name) {
        return null;
    }

    @Override
    public User getUser(@RequestParam("name") String name, @RequestParam("password") String password) {
        return new User("0", "0");
    }

    @Override
    public User getByPost(@RequestBody User user) {
        return new User("0", "0");
    }

}

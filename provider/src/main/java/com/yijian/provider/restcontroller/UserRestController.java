package com.yijian.provider.restcontroller;

import com.yijian.api.exception.ErrorCodeEnum;
import com.yijian.api.exception.ProviderException;
import com.yijian.api.model.User;
import com.yijian.api.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zmjiangi on 2018/5/28.
 */
@RestController
public class UserRestController implements UserService {

    @Override
    public String getName(@RequestParam("name") String name) {
        return name;
    }

    @Override
    public User get(@RequestParam("name") String name, @RequestParam("password") String password) {
        return new User(name, password);
    }

    @Override
    public User getByPost(@RequestBody User user) {
        return user;
    }

    @Override
    public void exception() {
        throw new ProviderException(ErrorCodeEnum.ERROR.getCode(), "提供者异常");
    }

}

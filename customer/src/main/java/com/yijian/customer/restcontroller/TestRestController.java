package com.yijian.customer.restcontroller;

import com.yijian.api.exception.ProviderException;
import com.yijian.api.model.User;
import com.yijian.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.yijian.api.exception.ErrorCodeEnum.ERROR;
import static com.yijian.customer.util.ResultUtils.fail;
import static com.yijian.customer.util.ResultUtils.success;

/**
 * Created by zmjiangi on 2018/5/28.
 */
@RestController
public class TestRestController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类

    @GetMapping("/api/test/getName")
    public String getName() {
        return userService.getName("name");
    }

    @GetMapping("/api/test/getUser")
    public User getUser() {
        User user = userService.get("name", "password");
        if (null != user) {
            System.out.println(user.getName() + "," + user.getPassword());
        }
        return user;
    }

    @PostMapping("/api/test/postUser")
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
    @GetMapping("/api/test/rest")
    public String restTest() {
        String providerMsg = restTemplate.getForEntity(
                "http://PROVIDER-SERVICE/svc/user/get?name=name&password=password",
                String.class
        ).getBody();

        return providerMsg;
    }

    @PostMapping("/api/test/restPost")
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

    @GetMapping("/api/test/exception")
    public ResponseEntity exception() {
        try {
            userService.exception();
        } catch (Exception e) {
            LOGGER.error("调用出错{}" + e);
            if (e instanceof ProviderException) {
                ProviderException providerException = (ProviderException) e;
                return fail(providerException.getCode(), providerException.getMessage());
            }
            return fail(ERROR.getCode(), ERROR.getMessage());
        }
        return success("调用成功");
    }

}

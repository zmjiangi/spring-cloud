package com.yijian.customer.handler;

import com.alibaba.fastjson.JSONObject;
import com.yijian.api.exception.ProviderException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by zmjiangi on 2018-5-29.
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    /**
     * 关于Feign触发阻断异常
     *
     * @param methodKey
     * @param response
     * @return
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String error = Util.toString(response.body().asReader());
            JSONObject jsonObject = JSONObject.parseObject(error);
            Integer code = jsonObject.getInteger("code");
            String message = jsonObject.getString("message");
            throw new ProviderException(code, message);
        } catch (IOException e) {
            LOGGER.error("[Feign解析异常] - [{}]", e);
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }

}


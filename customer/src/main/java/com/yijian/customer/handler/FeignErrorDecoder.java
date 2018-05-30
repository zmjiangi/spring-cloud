package com.yijian.customer.handler;

import com.yijian.api.exception.ErrorResponseEntity;
import com.yijian.api.exception.ProviderException;
import com.yijian.customer.util.JackSonUtil;
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

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String error = Util.toString(response.body().asReader());
            ErrorResponseEntity errorResponseEntity = JackSonUtil.jsonStrToBean(error, ErrorResponseEntity.class);
            Integer code = errorResponseEntity.getCode();
            String message = errorResponseEntity.getMessage();
            throw new ProviderException(code, message);
        } catch (IOException e) {
            LOGGER.error("[Feign解析异常] - [{}]", e);
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }

}


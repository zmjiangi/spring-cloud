package com.yijian.customer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by zmjiangi on 2018-5-30.
 */
public class JackSonUtil {

    /**
     * Json字符串转对象
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static  <T> T jsonStrToBean(String jsonStr, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, clazz);
    }

    /**
     * 对象转Json字符串
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static String beanToJsonStr(Object bean) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(bean);
    }

}

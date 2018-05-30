package com.yijian.customer.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zmjiangi on 2018-5-30.
 */
public class ResultUtils {

    public static ResponseEntity success(Object object) {
        return ResponseEntity.ok(object);
    }

    public static ResponseEntity fail(Integer code, String message) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", code);
        result.put("message", message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

}

package com.yijian.provider.handler;

import com.yijian.api.exception.ErrorCodeEnum;
import com.yijian.api.exception.ErrorResponseEntity;
import com.yijian.api.exception.ProviderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import static com.yijian.api.exception.ErrorCodeEnum.ERROR;

/**
 * Created by zmjiangi on 2018-5-29.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return new ResponseEntity<>(exception.getMessage(), status);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorResponseEntity handler(Exception exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        if (exception instanceof ProviderException) {
            ProviderException providerException = (ProviderException) exception;
            Integer code = providerException.getCode();
            String message = providerException.getMessage();
            if (code == null) {
                code = ErrorCodeEnum.ERROR.getCode();
            }
            return new ErrorResponseEntity(code, message);
        }
        return new ErrorResponseEntity(ERROR.getCode(), ERROR.getMessage());
    }

}

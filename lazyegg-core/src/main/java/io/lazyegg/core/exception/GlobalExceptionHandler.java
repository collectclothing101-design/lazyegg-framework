package io.lazyegg.core.exception;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.SysException;
import com.fasterxml.jackson.core.JacksonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandler
 *
 * @author DifferentW  nsmeng3@163.com
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {SysException.class})
    public ResponseEntity<Object> sysException(SysException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(Response.buildFailure("500", "系统异常请联系管理员"), HttpStatus.OK);
    }

    @ExceptionHandler(value = BizException.class)
    public ResponseEntity<Object> bizException(BizException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(Response.buildFailure("400", exception.getMessage()), HttpStatus.OK);
    }

    /**
     * 参数异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {IllegalArgumentException.class, JacksonException.class, NestedRuntimeException.class})
    public ResponseEntity<Object> illegalArgumentException(Exception exception) {
        String message = exception.getMessage();
        String errMessage = "参数异常-" + message;
        log.error(errMessage, exception);
        return new ResponseEntity<>(Response.buildFailure("500", errMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<Object> servletRequestBindingException(ServletRequestBindingException exception) {
        String message = exception.getMessage();
        String errMessage = "参数异常-" + message;
        log.error(errMessage, exception);
        return new ResponseEntity<>(Response.buildFailure("500", errMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}

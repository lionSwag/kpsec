package com.kpsec.test.exceptionHandler;

import com.kpsec.test.contoller.AccountController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(AccountController.PageNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Map<String, String> apiResultData = new LinkedHashMap<String,String>();
        apiResultData.put("code","404");
        apiResultData.put("메시지","br code not found error");
        return this.process(request, response, apiResultData, ex);
    }

    public ResponseEntity<Map<String, String>> process(HttpServletRequest request, HttpServletResponse response, Map<String, String> apiResultData , Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiResultData);
    }
}

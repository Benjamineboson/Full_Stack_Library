package com.example.library_fullstack.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AppResourceNotFoundException.class)
    public ModelAndView handleAppResourceNotFoundException(AppResourceNotFoundException ex){
        ModelAndView model = new ModelAndView();
        Map<Object, Object> map = new HashMap<>();
        map.put("statusName", HttpStatus.NOT_FOUND.getReasonPhrase());
        map.put("statusCode", HttpStatus.NOT_FOUND.value());
        map.put("message", ex.getMessage());
        model.addObject("modelMap", map);
        model.setViewName("exception-view");
        return model;
    }
}

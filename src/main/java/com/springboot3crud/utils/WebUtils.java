package com.springboot3crud.utils;


import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {

    public static HttpServletRequestWrapper getCurrentRequest() {
        return (HttpServletRequestWrapper) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}

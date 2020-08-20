package com.deway.blog.servlet.filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author Deway
 */
@WebFilter(
    urlPatterns = {"/*"},
    dispatcherTypes = {
        DispatcherType.ASYNC,
        DispatcherType.ERROR,
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE,
        DispatcherType.REQUEST
    },
    initParams ={
        @WebInitParam(name = "encoding", value = "UTF-8"),
        @WebInitParam(name = "forceRequestEncoding", value = "true"),
        @WebInitParam(name = "forceResponseEncoding", value = "true")
    }
)
public class EncodingFilter extends CharacterEncodingFilter {

}

package com.yihecloud.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName="AccessFilter",urlPatterns="/")
public class FilterGzip implements Filter {

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
   //     System.out.println("filter");

        chain.doFilter(new GzipRequestWrapper((HttpServletRequest) request),
                response);
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        
    }

}
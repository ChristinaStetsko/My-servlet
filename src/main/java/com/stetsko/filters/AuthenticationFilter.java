package com.stetsko.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final String AUTHENTICATION_KEY = "secured";
    private static final String AUTHENTICATION_ERROR = "{\n" +
            "        \"error\": \"Not valid authentication key\"\n" +
            "    }";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;

        String key = httpRequest.getHeader("Authorisation");

        if (AUTHENTICATION_KEY.equalsIgnoreCase(key)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.getWriter().print(AUTHENTICATION_ERROR);
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
        }
    }

    @Override
    public void destroy() {

    }
}

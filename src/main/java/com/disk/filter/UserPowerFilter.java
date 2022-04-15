package com.disk.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.disk.util.Constants.USER_SESSION;

public class UserPowerFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute(USER_SESSION) == null){
            resp.sendRedirect("/errorPages/userUnLogin.jsp");
        }

        filterChain.doFilter(req, resp);
    }

    public void destroy() {

    }
}

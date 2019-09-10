package com.xyq.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author xyq
 * @create 2019-09-10 08:07
 */
@WebFilter("/pages/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if(session.getAttribute("emp")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            request.setAttribute("msg","您还未登录,请先登录");
            request.setAttribute("url","/login.jsp");
            request.getRequestDispatcher("/forward.jsp").forward(servletRequest,servletResponse);
        }
    }

    public void destroy() {

    }
}

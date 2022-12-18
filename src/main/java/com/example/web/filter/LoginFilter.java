package com.example.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        //判断访问的资源是否与登录注册界面有关
        String[] urls = {"/login.jsp", "/imgs/", "/css/", "/loginServlet", "/register.jsp", "/registerServlet", "/checkCodeServlet", "/ajaxServlet"};
        String url = req.getRequestURL().toString();
        for (String u : urls){
            if(url.contains(u)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }

        //判断session中是否含有user
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            req.setAttribute("login_msg", "您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

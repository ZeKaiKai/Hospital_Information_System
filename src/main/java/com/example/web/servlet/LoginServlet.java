package com.example.web.servlet;

import com.example.pojo.User;
import com.example.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        User user = service.login(username, password);

        if (user != null) {
            //防止空指针异常，调用者是“1”永远不会为空
            if("1".equals(remember)){
                //确认勾选了"记住"，发送cookie
                //创建cookie
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                //设置存活时间一周
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                //将cookie存储到response中
                response.addCookie(c_password);
                response.addCookie(c_username);
            }
            //登录成功，用户对象存入到session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            request.getRequestDispatcher("/m.html").forward(request,response);
        }else{
            //登录失败，跳转到login.jsp，并且携带用户回去，提醒登录失败
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

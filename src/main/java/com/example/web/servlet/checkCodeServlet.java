package com.example.web.servlet;

import com.example.util.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream fos = response.getOutputStream();
        String s = CheckCodeUtil.outputVerifyImage(100, 50, fos, 4);
        //将生成的验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen", s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

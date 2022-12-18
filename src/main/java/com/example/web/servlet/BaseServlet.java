package com.example.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 * 替换HttpServlet
 * 从url获得方法名后，利用反射机制调用方法
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求路径
        String requestURI = req.getRequestURI();
        //2. 获取路径最后一段（调用的方法名）
        int index = requestURI.lastIndexOf("/");
        String methodName = requestURI.substring(index+1);

        //3. 通过获取的方法名，用反射调用方法
        //3.1 获取对应的servlet类
        Class<? extends BaseServlet> aClass = this.getClass();
        //3.2 获取方法对象并执行
        try {
            //获取方法对象
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

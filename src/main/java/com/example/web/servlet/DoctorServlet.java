package com.example.web.servlet;

import com.alibaba.fastjson.JSON;
import com.example.config.SpringConfig;
import com.example.pojo.Doctor;
import com.example.pojo.Patient;
import com.example.service.DoctorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctor/*")
public class DoctorServlet extends BaseServlet {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
    private DoctorService service = ctx.getBean(DoctorService.class);

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用服务查询所有
        List<Doctor> doctors = service.selectAll();
        //查询结果转为json
        String jsonString = JSON.toJSONString(doctors);
        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求数据
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        System.out.println(params);
        //转为doctor对象
        Doctor doctor = JSON.parseObject(params, Doctor.class);
        System.out.println(doctor);
        //调用服务查询所有
        List<Doctor> doctors = service.selectByCondition(doctor);
        System.out.println(doctors);
        //查询结果转为json
        String jsonString = JSON.toJSONString(doctors);
        System.out.println(jsonString);
        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求数据
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为doctor对象
        Doctor doctor = JSON.parseObject(params, Doctor.class);
        //调用服务更新数据
        service.updateDoctor(doctor);
        //返回成功信息
        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

    public void delDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求数据
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //调用服务删除数据
        service.delDoctor(params);
        //返回成功信息
        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");

    }

}

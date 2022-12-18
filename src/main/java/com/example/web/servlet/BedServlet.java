package com.example.web.servlet;

import com.alibaba.fastjson.JSON;
import com.example.config.SpringConfig;
import com.example.pojo.Bed;
import com.example.pojo.Patient;
import com.example.service.BedService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/bed/*")
public class BedServlet extends BaseServlet {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
    private BedService service = ctx.getBean(BedService.class);

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用服务查询所有
        List<Bed> beds = service.selectAll();

        //查询结果转为json
        String jsonString = JSON.toJSONString(beds);
        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取请求数据
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为bed对象
        Bed bed = JSON.parseObject(params, Bed.class);
        //调用服务查询所有
        List<Bed> beds = service.selectByCondition(bed);
        //查询结果转为json
        String jsonString = JSON.toJSONString(beds);
        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}

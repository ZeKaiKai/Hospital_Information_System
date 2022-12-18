package com.example.web.servlet;

import com.alibaba.fastjson.JSON;
import com.example.config.SpringConfig;
import com.example.pojo.Patient;
import com.example.service.PatientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/patient/*")
public class PatientServlet extends BaseServlet {

    private ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
    private PatientService service = ctx.getBean(PatientService.class);

    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用服务
        service.test("200000000000000007");
    }

    public void getAvailableRidByDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 读取到id
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        System.out.println(params);
        // 调用服务，返回可用房间号
        List<String> availableRidByDept = service.getAvailableRidByDept(params);
        System.out.println(availableRidByDept);
        //写入JSON并返回
        String jsonString = JSON.toJSONString(availableRidByDept);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void getAvailableBidByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 读取到id
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        System.out.println(params);

        // 调用服务，返回可用房间号
        List<String> AvailableBid = service.getAvailableBidByRid(params);
        System.out.println(AvailableBid);

        //写入JSON并返回
        String jsonString = JSON.toJSONString(AvailableBid);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 查询未住院的病人
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用服务查询所有
        List<Patient> patients = service.SelectAll();
        List<Patient> hospitalized = new ArrayList<>();
        // 筛选掉没住院的病人
        for (Patient patient : patients){
            if (patient.getpRoomID() == null){
                hospitalized.add(patient);
            }
        }

        //查询结果转为json
        String jsonString = JSON.toJSONString(hospitalized);

        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 查询所有住院病人
    public void selectHospitalized(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用服务查询所有住院病人
        List<Patient> patients = service.selectHospitalized();

        //查询结果转为json
        String jsonString = JSON.toJSONString(patients);

        //设置编码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 查询未入院病人
    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为patient对象
        Patient patient = JSON.parseObject(params, Patient.class);

        //调用服务查询所有
        List<Patient> patients = service.SelectAll();
        List<Patient> hospitalized = new ArrayList<>();
        // 筛选掉没住院的病人
        for (Patient p : patients){
            if (p.getpRoomID() == null){
                // 双条件查询
                if (patient.getpName()!=null && patient.getpSexual()!=null){
                    if (p.getpName().equals(patient.getpName()) && p.getpSexual().equals(patient.getpSexual())){
                        hospitalized.add(p);
                    }
                }
                // 单条件查询
                else{
                    if (p.getpName().equals(patient.getpName()) || p.getpSexual().equals(patient.getpSexual())){
                        hospitalized.add(p);
                    }
                }
            }
        }
        //写入JSON并返回
        String jsonString = JSON.toJSONString(hospitalized);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 查询入院病人
    public void selectHospitalizedByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为patient对象
        Patient patient = JSON.parseObject(params, Patient.class);

        List<Patient> patients = service.selectHospitalized();

        List<Patient> hospitalized = new ArrayList<>();
        for (Patient p : patients){
            if (p.getpName().equals(patient.getpName()) ||
                p.getpSexual().equals(patient.getpSexual()) ||
                p.getpRoomID().equals(patient.getpRoomID()) ||
                p.getpBedId().equals(patient.getpBedId()) ||
                p.getpDeptId().equals(patient.getpDeptId())){
                hospitalized.add(p);
            }
        }

        //写入JSON并返回
        String jsonString = JSON.toJSONString(hospitalized);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

        /**
         * 返回患者科室统计信息
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException
         */
    public void FindPatientData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有病人
        List<Patient> patients = service.selectHospitalized();
        //查询结果存入map中
        Map<String, Integer> res = new HashMap<>();

        //统计病人科室
        for(Patient patient : patients){
            String s = service.selectByDept(Integer.valueOf(patient.getpDeptId()));
            //第一次遇到这个科室
            if(!res.containsKey(s)){
                res.put(s, 1);
            }else{
                int count = res.get(s);
                res.put(s,count+1);
            }
        }
        String jsonString = JSON.toJSONString(res);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updatePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为patient对象
        Patient patient = JSON.parseObject(params, Patient.class);
        //调用服务更新数据
        service.updatePatient(patient);
        //返回成功信息
        response.getWriter().write("success");
    }

    /**
     * 仅传入身份证号码和名字，实现插入患者
     * 自动计算年龄和性别，自动根据数据库分配房间号和床位。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void insertPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为patient对象
        Patient patient = JSON.parseObject(params, Patient.class);

        //调用服务更新数据
        service.insertHospitalized(patient);
        service.insertPatient(patient);
        //返回成功信息
        response.getWriter().write("success");
    }



    public void delPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //调用服务删除数据
        Patient patient = JSON.parseObject(params, Patient.class);
        String pid = patient.getpID();
        service.delPatient(pid);
        //返回成功信息
        response.getWriter().write("success");
    }

    public void outHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从ajax的data中读取数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        // 转换为patient对象
        Patient patient = JSON.parseObject(params, Patient.class);
        String pid = patient.getpID();
        // 调用出院服务
        service.outHospital(pid);
        response.getWriter().write("success");
    }

}


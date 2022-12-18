package com.example.service;

import com.example.pojo.Doctor;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    /**
     * 查询所有
     * @return
     */
    public List<Doctor> selectAll();

    /**
     * 条件查询
     * @return
     */
    public List<Doctor> selectByCondition(Doctor doctor);

    /**
     * 更新医生信息
     * @param doctor
     */
    public void updateDoctor(Doctor doctor);

    /**
     * 根据id删除医生
     * @param id
     */
    public void delDoctor(String id);
}

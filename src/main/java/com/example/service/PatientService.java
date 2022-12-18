package com.example.service;

import com.example.pojo.Patient;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface PatientService {

    void test(String id);

    /**
     * 查询所有
     * @return
     */
    List<Patient> SelectAll();

    /**
     * 选择所有住院病人
     * @return
     */
    List<Patient> selectHospitalized();

    /**
     * 根据deptid查询可用房间号
     * @param id
     * @return
     */
    List<String> getAvailableRidByDept(String id);

    /**
     * 传入roomid，查询可用床位号
     * @param id
     * @return
     */
    List<String> getAvailableBidByRid(String id);

    /**
     * 添加患者信息d
     */
    void insertPatient(Patient patient);

    /**
     * 增加入院病人
     */
    void insertHospitalized(Patient patient);

    /**
     * 条件查询
     * @param brand
     * @return
     */
    List<Patient> selectByCondition(Patient brand);

    /**
     * 住院病人的条件查询
     * @param patient
     * @return
     */
    List<Patient> selectByCondition_hospitalized(Patient patient);

    /**
     * 根据deptID查询科室名
     * @param deptID
     * @return
     */
    String selectByDept(int deptID);

    /**
     * 更新患者信息
     * @param patient
     */
    void updatePatient(Patient patient);

    /**
     * 通过住院号删除患者
     * @param pID
     */
    void delPatient(String pID);

    /**
     * 出院操作
     * @param pID
     */
    void outHospital(String pID);

}

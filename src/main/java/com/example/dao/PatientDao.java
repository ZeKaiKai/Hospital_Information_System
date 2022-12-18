package com.example.dao;

import com.example.pojo.Patient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PatientDao {

    @Select("call DelPatient(#{id})")
    void test(String id);

    /**
     * 选择两个表中的所有记录
     * @return
     */
    @Select("select * from patient LEFT JOIN hospitalized ON hospitalized.pID=patient.pID")
    List<Patient> selectAll();

    /**
     * 选择所有住院病人
     * @return
     */
    @Select("select * from hospitalized,patient where hospitalized.pID=patient.pID")
    List<Patient> selectHospitalized();

    /**
     * 根据deptid查询可用房间号
     * @param id
     * @return
     */
    @Select("call getAvailableRidByDept(#{id})")
    List<String> getAvailableRidByDept(String id);

    /**
     * 传入roomid，查询可用床位号
     * @param id
     * @return
     */
    @Select("call getAvailableBidByRid(#{id})")
    List<String> getAvailableBidByRid(String id);

    /**
     * 新增病人（仅传入身份证号码，姓名，既往病史/用药过敏）
     * 将数据插入到patient表和hospitalized表
     * @return
     */
    @Select("call AddPatient(#{pID}, #{pName}, #{pNotice})")
    List<Patient> insertPatient(Patient patient);

    /**
     * 增加入院病人
     */
    @Select("call InHosp(#{pID}, #{pRoomID}, #{pBedId}, #{pDeptId})")
    void insertHospitalized(Patient patient);

    /**
     * 条件查询
     * @param patient
     * @return
     */
    @Select("SELECT * from patient " +
            "where pName like #{pName} " +
            "or pSexual = #{pSexual} ")
    List<Patient> selectByCondition(Patient patient);

    /**
     * 住院病人的条件查询
     * @param patient
     * @return
     */
    @Select("SELECT * from hospitalized where pName like #{pName} " +
            "or pSexual = #{pSexual} " +
            "or pRoomId = #{pRoomID} " +
            "or pBedId = #{pBedId} ")
    List<Patient> selectByCondition_hospitalized(Patient patient);

    @Select("select deptName from dept where deptID = #{deptID}")
    String selectByDept(int deptID);

    /**
     * 修改病人信息，通过隐式传入的pID
     * @param patient
     */
    @Update("update patient set " +
            "pName=#{pName}," +
            "pSexual=#{pSexual}," +
            "pAge=#{pAge}," +
            "pRoomId=#{pRoomId}," +
            "pBedId=#{pBedId} " +
            "where pID=#{pID}")
    void updatePatient(Patient patient);

    /**
     * 通过隐式传入的pID删除用户
     * @param pID
     */
    @Delete("delete from patient where pID = #{pID}")
    void delPatientById(String pID);

    /**
     * 出院操作
     * @param pID
     */
    @Select("call OutHosp(#{pID})")
    void outHospital(String pID);
}


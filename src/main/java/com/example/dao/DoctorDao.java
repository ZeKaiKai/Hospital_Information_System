package com.example.dao;

import com.example.pojo.Doctor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DoctorDao {
    @Select("select * from doctor")
    List<Doctor> selectAll();

    @Select("select * from doctor where " +
            "dID like #{dID} " +
            "or dName like #{dName} " +
            "or sexual like #{sexual} " +
            "or title like #{title} ")
    List<Doctor> selectByCondition(Doctor doctor);

    @Update("update doctor set dID=#{dID},dName=#{dName},sexual=#{sexual}," +
            "age=#{age},title=#{title},deptID=#{deptID} " +
            "where dID = #{dID}")
    void updateDoctor(Doctor doctor);

    @Delete("delete from doctor where dID = #{id}")
    void delDoctor(String id);
}

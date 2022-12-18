package com.example.dao;

import com.example.pojo.Bed;
import com.example.pojo.Doctor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BedDao {

    @Select("select * from Bed")
    List<Bed> selectAll();

    @Select("select * from bed where " +
            "bedID = #{bedID} " +
            "or roomID = #{roomID} " +
            "or statu = #{statu}")
    List<Bed> selectByCondition(Bed bed);

    @Update("update bed set statu = #{statu} where bedID = #{id}")
    void update(@Param("statu")String statu, @Param("id")String id);
}

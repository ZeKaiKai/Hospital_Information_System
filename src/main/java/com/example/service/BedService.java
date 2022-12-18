package com.example.service;

import com.example.pojo.Bed;

import java.util.List;

public interface BedService {

    /**
     * 查询所有
     * @return
     */
    List<Bed> selectAll();

    /**
     * 条件查询床位
     * @return
     */
    List<Bed> selectByCondition(Bed bed);

    /**
     * 根据id更改床的状态
     * @param id
     */
    void updateBed(String id);

}

package com.example.service.impl;

import com.example.dao.BedDao;
import com.example.pojo.Bed;
import com.example.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl implements BedService {
    @Autowired
    private com.example.dao.BedDao bedDao;

    @Override
    public List<Bed> selectAll() {
        return bedDao.selectAll();
    }

    @Override
    public List<Bed> selectByCondition(Bed bed) {
        return bedDao.selectByCondition(bed);
    }

    @Override
    public void updateBed(String id) {

        //先用其它服务查到床的状态，然后更改成相反的状态
        Bed bed = new Bed();
        bed.setBedID(id);
        System.out.println(bed);
        List<Bed> beds = bedDao.selectByCondition(bed);
        Bed bed1 = beds.get(0);
        System.out.println(bed1);
        String statu = bed1.getStatu();
        System.out.println(statu);

        if("1".equals(bed1.getStatu())){
            bedDao.update("0", id);
        }else{
            bedDao.update("1", id);
        }

    }


}

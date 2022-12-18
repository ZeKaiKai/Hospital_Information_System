package com.example.service.impl;

import com.example.pojo.Doctor;
import com.example.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private com.example.dao.DoctorDao DoctorDao;

    @Override
    public List<Doctor> selectAll() {
        return DoctorDao.selectAll();
    }

    @Override
    public List<Doctor> selectByCondition(Doctor doctor) {
        return DoctorDao.selectByCondition(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        DoctorDao.updateDoctor(doctor);
    }

    public void delDoctor(String id){
        DoctorDao.delDoctor(id);
    }
}

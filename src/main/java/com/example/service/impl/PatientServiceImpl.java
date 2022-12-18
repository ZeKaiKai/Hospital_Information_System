package com.example.service.impl;

import com.example.dao.PatientDao;
import com.example.pojo.Patient;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientDao patientDao;

    @Override
    public void test(String id) {
        patientDao.test(id);
    }

    @Override
    public List<Patient> SelectAll() {
        return patientDao.selectAll();
    }

    @Override
    public List<Patient> selectHospitalized() {
        return patientDao.selectHospitalized();
    }

    @Override
    public List<String> getAvailableRidByDept(String id) {
        return patientDao.getAvailableRidByDept(id);
    }

    @Override
    public List<String> getAvailableBidByRid(String id) {
        return patientDao.getAvailableBidByRid(id);
    }

    @Override
    public List<Patient> selectByCondition(Patient patient) {
        return patientDao.selectByCondition(patient);
    }

    @Override
    public List<Patient> selectByCondition_hospitalized(Patient patient) {
        return patientDao.selectByCondition_hospitalized(patient);
    }

    @Override
    public String selectByDept(int deptID) {
        return patientDao.selectByDept(deptID);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientDao.updatePatient(patient);
    }

    @Override
    public void insertPatient(Patient patient) {
        patientDao.insertPatient(patient);
    }

    @Override
    public void insertHospitalized(Patient patient) {
        patientDao.insertHospitalized(patient);
    }

    @Override
    public void delPatient(String pID) {
        patientDao.delPatientById(pID);
    }

    @Override
    public void outHospital(String pID) {
        patientDao.outHospital(pID);
    }


}

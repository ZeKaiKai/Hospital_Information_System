package com.example.pojo;

public class Doctor {
    private String dID;
    private String dName;
    private String sexual;
    private String age;
    private String title;
    private String deptID;

    public String getdID() {
        return dID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "dID='" + dID + '\'' +
                ", dName='" + dName + '\'' +
                ", sexual='" + sexual + '\'' +
                ", age='" + age + '\'' +
                ", title='" + title + '\'' +
                ", deptID='" + deptID + '\'' +
                '}';
    }
}

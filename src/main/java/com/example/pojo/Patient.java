package com.example.pojo;

public class Patient {
    private String pID;
    private String pName;
    private String pAge;
    private String pSexual;
    private String pRoomID;
    private String pBedId;
    private String pDeptId;
    private String pNotice;
    private String pInTime;

    @Override
    public String toString() {
        return "Patient{" +
                "pID='" + pID + '\'' +
                ", pName='" + pName + '\'' +
                ", pAge='" + pAge + '\'' +
                ", pSexual='" + pSexual + '\'' +
                ", pRoomID='" + pRoomID + '\'' +
                ", pBedId='" + pBedId + '\'' +
                ", pDeptId='" + pDeptId + '\'' +
                ", pNotice='" + pNotice + '\'' +
                ", pInTime='" + pInTime + '\'' +
                '}';
    }

    public String getpInTime() {
        return pInTime;
    }

    public void setpInTime(String pInTime) {
        this.pInTime = pInTime;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAge() {
        return pAge;
    }

    public void setpAge(String pAge) {
        this.pAge = pAge;
    }

    public String getpSexual() {
        return pSexual;
    }

    public void setpSexual(String pSexual) {
        this.pSexual = pSexual;
    }

    public String getpRoomID() {
        return pRoomID;
    }

    public void setpRoomID(String pRoomID) {
        this.pRoomID = pRoomID;
    }

    public String getpBedId() {
        return pBedId;
    }

    public void setpBedId(String pBedId) {
        this.pBedId = pBedId;
    }

    public String getpDeptId() {
        return pDeptId;
    }

    public void setpDeptId(String pDeptId) {
        this.pDeptId = pDeptId;
    }

    public String getpNotice() {
        return pNotice;
    }

    public void setpNotice(String pNotice) {
        this.pNotice = pNotice;
    }
}

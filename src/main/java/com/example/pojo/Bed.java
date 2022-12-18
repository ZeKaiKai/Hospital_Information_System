package com.example.pojo;

public class Bed {
    private String bedID;
    private String roomID;
    private String statu;

    public String getBedID() {
        return bedID;
    }

    public void setBedID(String bedID) {
        this.bedID = bedID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "bedID='" + bedID + '\'' +
                ", roomID='" + roomID + '\'' +
                ", statu='" + statu + '\'' +
                '}';
    }
}

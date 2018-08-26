package com.example.zaidshaharil.testfinger1;

import java.io.Serializable;

public class Vehicle implements Serializable {
    public String id;
    public String plateNo;
    public String deviceid;
    public String description;
    public String status;

    public Vehicle() {
        super();
    }

    public Vehicle(String id, String deviceid, String description, String plateNo, String status) {
        this.id = id;
        this.plateNo = plateNo;
        this.deviceid = deviceid;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", plateNo='" + plateNo + '\'' +
                ", deviceid='" + deviceid + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

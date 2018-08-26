package com.example.zaidshaharil.testfinger1;

import java.io.Serializable;

public class Detainee implements Serializable {
    private int id;
    private String name;
    private String deviceId;
    private String status;

    public Detainee(int id, String name, String deviceId, String status) {
        this.id = id;
        this.name = name;
        this.deviceId = deviceId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Detainee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

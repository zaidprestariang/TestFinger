package com.example.zaidshaharil.testfinger1;

import java.io.Serializable;

public class Geometry implements Serializable {
    private Double x;
    private Double y;
    private Double coordinates[];
    private String type;

    public Geometry(){ super();}

    public Geometry(Double x, Double y, Double[] coordinates, String type) {
        this.x = x;
        this.y = y;
        this.coordinates = coordinates;
        this.type = type;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

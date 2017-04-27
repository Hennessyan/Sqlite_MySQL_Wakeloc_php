package com.llu17.youngq.sqlite_gps.table;

/**
 * Created by youngq on 17/3/29.
 */

public class ACCELEROMETER {
    private String id;
    private long timestamp;
    private double x;
    private double y;
    private double z;

    public ACCELEROMETER(double z, String id, long timestamp, double x, double y) {
        this.id = id;
        this.timestamp = timestamp;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ACCELEROMETER() {}

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "ACCELEROMETER: {" + " id: " + id + " timestamp: " + timestamp + " x: "
                + x + " y: " + y +  " z: " + z + " }";
    }
}

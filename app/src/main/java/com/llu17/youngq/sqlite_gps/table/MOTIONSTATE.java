package com.llu17.youngq.sqlite_gps.table;

/**
 * Created by youngq on 17/3/29.
 */

public class MOTIONSTATE {
    private String id;
    private long timestamp;
    private int state;

    public MOTIONSTATE(String id, long timestamp, int state) {
        this.id = id;
        this.timestamp = timestamp;
        this.state = state;
    }

    public MOTIONSTATE() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "MOTIONSTATE: {" + " id: " + id + " timestamp: " + timestamp + " state: "
                + state + " }";
    }
}

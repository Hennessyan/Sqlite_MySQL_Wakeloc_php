package com.llu17.youngq.sqlite_gps.table;

/**
 * Created by youngq on 17/4/14.
 */

public class WIFI {
    private String id;
    private long timestamp;
    private int state;

    public WIFI(String id, int state, long timestamp) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
    }

    public WIFI() {}

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
        return "WIFI: {" + " id: " + id + " timestamp: " + timestamp + " state: "
                + state + "% }";
    }
}

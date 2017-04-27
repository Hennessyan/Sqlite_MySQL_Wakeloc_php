package com.llu17.youngq.sqlite_gps.table;

/**
 * Created by youngq on 17/3/29.
 */

public class STEP {
    private String id;
    private long timestamp;
    private int count;

    public STEP(String id, long timestamp, int count) {
        this.id = id;
        this.timestamp = timestamp;
        this.count = count;
    }

    public STEP() {}

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "STEP: {" + " id: " + id + " timestamp: " + timestamp + " count: "
                + count + " }";
    }
}

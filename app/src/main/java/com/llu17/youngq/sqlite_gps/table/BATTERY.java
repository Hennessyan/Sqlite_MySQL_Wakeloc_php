package com.llu17.youngq.sqlite_gps.table;

/**
 * Created by youngq on 17/4/14.
 */

public class BATTERY {
    private String id;
    private long timestamp;
    private int percentage;

    public BATTERY(String id, int percentage, long timestamp) {
        this.id = id;
        this.percentage = percentage;
        this.timestamp = timestamp;
    }

    public BATTERY() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BATTERY: {" + " id: " + id + " timestamp: " + timestamp + " percentage: "
                + percentage + "% }";
    }
}

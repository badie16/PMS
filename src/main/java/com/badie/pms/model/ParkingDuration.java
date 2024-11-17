package com.badie.pms.model;

import java.sql.Timestamp;

public class ParkingDuration {
    private  int duration_id;
    private Timestamp duration_added_on,duration_updated_on;
    private int duration_value;

    public ParkingDuration(int duration_id, int duration_value, Timestamp duration_added_on, Timestamp duration_updated_on) {
        this(duration_id,duration_value);
        this.duration_updated_on = duration_updated_on;
        this.duration_added_on = duration_added_on;
    }

    public ParkingDuration(int duration_id, int duration_value) {
        this.duration_id = duration_id;
        this.duration_value = duration_value;
    }

    public Timestamp getDuration_added_on() {
        return duration_added_on;
    }
    public Timestamp getDuration_updated_on() {
        return duration_updated_on;
    }

    public int getDuration_value() {
        return duration_value;
    }

    public void setDuration_value(int duration_value) {
        this.duration_value = duration_value;
    }

    public int getDuration_id() {
        return duration_id;
    }

    @Override
    public String toString() {
        return "ParkingDuration{" +
                "duration_id=" + duration_id +
                ", duration_added_on=" + duration_added_on +
                ", duration_updated_on=" + duration_updated_on +
                ", duration_value='" + duration_value + '\'' +
                '}';
    }
}

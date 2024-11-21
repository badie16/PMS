package com.badie.pms.model;

import com.badie.pms.db.PrgDurationDb;
import com.badie.pms.db.VehCategoryDb;

import java.sql.Timestamp;

public class ParkingPrice {
    private final int price_id;
    private int category_id;
    private int duration_id;
    private String category_name;
    private int duration_value;
    private double price_value;
    private final Timestamp price_added_on;
    private final Timestamp price_updated_on;
    public ParkingPrice(int price_id, int category_id, int duration_id, double price_value, Timestamp price_added_on, Timestamp price_updated_on) {
        this.price_id = price_id;
        this.category_id = category_id;
        this.duration_id = duration_id;
        this.price_value = price_value;
        this.price_added_on = price_added_on;
        this.price_updated_on = price_updated_on;
        VehicleCategory vehicleCategory= new VehCategoryDb().getVehicleCategory(category_id);
        if (vehicleCategory == null) {
            category_name =  "NUll";
        }else {
            category_name =  vehicleCategory.getCategory_name();
        }
        ParkingDuration parkingDuration = new PrgDurationDb().getParkingDuration(duration_id);
        if (parkingDuration == null) {
            duration_value = 0;
        }else {
            duration_value = parkingDuration.getDuration_value();
        }
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getDuration_id() {
        return duration_id;
    }
    public String getCategory_name() {
        return category_name;
    }

    public int getDuration_value() {
        return duration_value;
    }

    public int getPrice_id() {
        return price_id;
    }

    public double getPrice_value() {
        return price_value;
    }

    public Timestamp getPrice_added_on() {
        return price_added_on;
    }

    public Timestamp getPrice_updated_on() {
        return price_updated_on;
    }

    public void setPrice_value(double price_value) {
        this.price_value = price_value;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setDuration_id(int duration_id) {
        this.duration_id = duration_id;
    }

    @Override
    public String toString() {
        return "ParkingPrice{" +
                "price_id=" + price_id +
                ", category_id=" + category_id +
                ", duration_id=" + duration_id +
                ", price_value=" + price_value +
                ", price_added_on=" + price_added_on +
                ", price_updated_on=" + price_updated_on +
                '}';
    }
}

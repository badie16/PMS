package com.badie.pms.model;

import java.sql.Timestamp;

public class ParkingCategory {
    private int category_id;
    private Timestamp category_added_on,category_updated_on;
    private String category_name;
    public ParkingCategory(int categoryId, String categoryName) {
        this(categoryName);
        category_id = categoryId;
    }
    public ParkingCategory(int category_id, String category_name, Timestamp category_added_on, Timestamp category_updated_on) {
        this(category_id,category_name);
        this.category_added_on = category_added_on;
        this.category_updated_on = category_updated_on;
    }
    public ParkingCategory(String category_name) {
        this.category_name = category_name;
    }
    public int getCategory_id() {
        return category_id;
    }
    public Timestamp getCategory_added_on() {
        return category_added_on;
    }
    public Timestamp getCategory_updated_on() {
        return category_updated_on;
    }
    public String getCategory_name() {
        return category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "ParkingCategory{ " +
                "category_id= " + category_id +
                ", category_name= '" + category_name +
                ", category_added_on= " + category_added_on +
                ", category_updated_on= " + category_updated_on +
                " " + '\'' +
                '}';
    }
}

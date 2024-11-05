package com.badie.pms;

import com.badie.pms.db.VehCategoryDb;

public class AppTest {
    public static void main(String[] args) {
        VehCategoryDb vehCategoryDb = new VehCategoryDb();
        System.out.println(vehCategoryDb.addVehicleCategory("hh"));
    }
}

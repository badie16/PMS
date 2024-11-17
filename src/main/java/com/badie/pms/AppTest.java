package com.badie.pms;

import com.badie.pms.db.PrgDurationDb;
import com.badie.pms.db.VehCategoryDb;

import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        PrgDurationDb p = new PrgDurationDb();
        System.out.println(p.exitParkingDurationValue(1));

    }
}

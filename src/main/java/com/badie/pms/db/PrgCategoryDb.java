package com.badie.pms.db;

import com.badie.pms.model.ParkingCategory;
import com.badie.pms.model.User;

import java.io.FilterInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrgCategoryDb {
    Connection con = MyConnection.connection();
    String sql;
    PreparedStatement ps;
    Statement st;

    public ParkingCategory getParkingCategory(int category_id){
        ParkingCategory parkingCategory = null;
        sql="SELECT * FROM parking_category WHERE category_id = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                parkingCategory = new ParkingCategory(
                        category_id,
                        rs.getString("category_name"),
                        rs.getTimestamp("category_added_on"),
                        rs.getTimestamp("category_updated_on")
                );
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return parkingCategory;
    }
    public List<ParkingCategory> getListOfCategory(){
        List<ParkingCategory> categoryList = new ArrayList<>();
        sql="SELECT category_id FROM parking_category order by category_id DESC";
        try {
            st = con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                categoryList.add(getParkingCategory(rs.getInt("category_id")));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return categoryList;
    }
}

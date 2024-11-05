package com.badie.pms.db;

import com.badie.pms.model.VehicleCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehCategoryDb {
    Connection con = MyConnection.connection();
    String sql;
    PreparedStatement ps;
    Statement st;

    public VehicleCategory getVehicleCategory(int category_id){
        VehicleCategory vehicleCategory = null;
        sql="SELECT * FROM parking_category WHERE category_id = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                vehicleCategory = new VehicleCategory(
                        category_id,
                        rs.getString("category_name"),
                        rs.getTimestamp("category_added_on"),
                        rs.getTimestamp("category_updated_on")
                );
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return vehicleCategory;
    }
    public List<VehicleCategory> getListOfCategory(){
        List<VehicleCategory> categoryList = new ArrayList<>();
        sql="SELECT category_id FROM parking_category order by category_id DESC";
        try {
            st = con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                categoryList.add(getVehicleCategory(rs.getInt("category_id")));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return categoryList;
    }

    public boolean addVehicleCategory(String category_name){
        String sql = "INSERT INTO `parking_category` (`category_name`) VALUE (?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, category_name);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return false;
    }
}

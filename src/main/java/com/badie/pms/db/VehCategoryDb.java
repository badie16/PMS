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

    public int getCategoryIdByName(String categoryName) {
        String sql = "SELECT category_id FROM parking_category WHERE category_name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("category_id");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'ID de la catégorie : " + e.getMessage());
        }
        return -1; // Retourner -1 si non trouvé
    }
    public String getCategoryNameById(int category_id) {
        String sql = "SELECT category_name FROM parking_category WHERE category_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("category_name");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de le nom de la catégorie : " + e.getMessage());
        }
        return null; // Retourner null si non trouvé
    }
}

package com.badie.pms.db;

import com.badie.pms.model.ParkingPrice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrgPriceDb {
    Connection con = MyConnection.connection();

    PreparedStatement ps;
    Statement st;
    /**
     * Récupère un prix de parking par son ID.
     *
     * @param price_id L'ID du prix à récupérer.
     * @return Un objet ParkingPrice ou null en cas d'erreur.
     */
    public ParkingPrice getParkingPrice(int price_id){
        ParkingPrice parkingPrice = null;
        String sql="SELECT * FROM parking_price WHERE price_id = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, price_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                parkingPrice = new ParkingPrice(
                        price_id,
                        rs.getInt("category_id"),
                        rs.getInt("duration_id"),
                        rs.getDouble("price_value"),
                        rs.getTimestamp("price_added_on"),
                        rs.getTimestamp("price_updated_on")
                );
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return parkingPrice;
    }

    /**
     * Récupère la liste de tous les prix de parking.
     *
     * @return Une liste d'objets ParkingPrice.
     */
    public List<ParkingPrice> getListOfPrices(){
        List<ParkingPrice> priceList = new ArrayList<>();
        String sql="SELECT price_id FROM parking_price order by price_id DESC";
        try {
            st = con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                priceList.add(getParkingPrice(rs.getInt("price_id")));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return priceList;
    }

    /**
     * Ajoute un nouveau prix de parking dans la base de données.
     *
     * @param category_id L'ID de la catégorie de véhicule.
     * @param duration_id L'ID de la durée.
     * @param price_value La valeur du prix.
     * @return True si l'insertion réussit, sinon False.
     */
    public boolean addParkingPrice(int category_id, int duration_id, double price_value) {
        String sql = "INSERT INTO parking_price (category_id, duration_id, price_value) VALUES (?, ?, ?)";
        try  {
            ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ps.setInt(2, duration_id);
            ps.setDouble(3, price_value);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'un nouveau prix : " + e.getMessage());
        }
        return false;
    }

    /**
     * chick if couple category_id And duration_id exist in db
     *
     * @param category_id L'ID de la catégorie de véhicule.
     * @param duration_id L'ID de la durée.
     * @return True si exist couple category_id And duration_id in database, sinon False.
     */
    public boolean exitParkingPriceValue(int category_id, int duration_id){
        String sql="SELECT * FROM parking_price WHERE duration_id  = ? AND category_id  = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, duration_id);
            ps.setInt(2, category_id);
            return  ps.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return false;
    }
    public boolean exitParkingPriceValue(int price_id){
        String sql="SELECT * FROM parking_price WHERE price_id  = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, price_id);
            return  ps.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return false;
    }
    public boolean deleteParkingPrice(int price_id) {
        if (!exitParkingPriceValue(price_id)){
            return false;
        }
        String sql = "DELETE FROM parking_price WHERE price_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, price_id);
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            System.out.println("error " + e);
        }
        return false;
    }
    public boolean updateParkingPrice(int priceId, int categoryId, int durationId, double priceValue) {
        String sql = "UPDATE parking_price SET category_id = ?, duration_id = ?, price_value = ? WHERE price_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, durationId);
            ps.setDouble(3, priceValue);
            ps.setInt(4, priceId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating parking price: " + e.getMessage());
            return false;
        }
    }

}

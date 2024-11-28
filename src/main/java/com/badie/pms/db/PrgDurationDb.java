package com.badie.pms.db;

import com.badie.pms.model.ParkingDuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrgDurationDb {
    Connection con = MyConnection.connection();
    PreparedStatement ps;
    Statement st;
    public ParkingDuration getParkingDuration(int duration_id){
        ParkingDuration parkingDuration = null;
        String sql="SELECT * FROM parking_duration WHERE duration_id = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, duration_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                parkingDuration = new ParkingDuration(
                        duration_id,
                        rs.getInt("duration_value"),
                        rs.getTimestamp("duration_added_on"),
                        rs.getTimestamp("duration_updated_on")
                );
            }

        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return parkingDuration;
    }
    public boolean exitParkingDurationValue(int duration_value){
        String sql="SELECT * FROM parking_duration WHERE duration_value = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, duration_value);
            return  ps.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return false;
    }
    public List<ParkingDuration> getListOfDuration(){
        List<ParkingDuration> durationList = new ArrayList<>();
        String sql="SELECT duration_id FROM parking_duration order by duration_id DESC";
        try {
            st = con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
                durationList.add(getParkingDuration(rs.getInt("duration_id")));
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return durationList;
    }

    public boolean addParkingDuration(String duration_value){
        String sql = "INSERT INTO `parking_duration` (`duration_value`) VALUE (?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, duration_value);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return false;
    }

    public int getDurationIdByValue(int durationValue) {
        String sql = "SELECT duration_id FROM parking_duration WHERE duration_value = ?";
        try  {
            ps = con.prepareStatement(sql);
            ps.setInt(1, durationValue);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("duration_id");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'ID de la durée : " + e.getMessage());
        }
        return -1; // Retourner -1 si non trouvé
    }
    public boolean deleteParkingDuration(int durationId) {
        String sql = "DELETE FROM parking_duration WHERE duration_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, durationId); // Définir l'ID de la durée à supprimer
            int rowsAffected = ps.executeUpdate(); // Exécuter la requête
            return rowsAffected > 0; // Retourne true si au moins une ligne est affectée
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la durée de stationnement : " + e.getMessage());
        }
        return false; // Retourne false en cas d'échec
    }
    public boolean updateParkingDuration(int durationId, int newDurationValue) {
        String sql = "UPDATE parking_duration SET duration_value = ?, duration_updated_on = CURRENT_TIMESTAMP WHERE duration_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, newDurationValue); // Nouvelle valeur de la durée
            ps.setInt(2, durationId); // ID de la durée à mettre à jour
            return ps.executeUpdate() > 0; // Retourne true si au moins une ligne est mise à jour
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la durée : " + e.getMessage());
        }
        return false; // Retourne false en cas d'échec
    }
    public boolean existsById(int durationId) {
        String sql = "SELECT duration_id FROM parking_duration WHERE duration_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, durationId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Retourne true si un résultat est trouvé
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'existence de la durée : " + e.getMessage());
        }
        return false; // Retourne false en cas d'échec ou si non trouvé
    }

}

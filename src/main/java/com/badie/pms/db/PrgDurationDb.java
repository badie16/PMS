package com.badie.pms.db;

import com.badie.pms.model.ParkingDuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrgDurationDb {
    Connection con = MyConnection.connection();
    String sql;
    PreparedStatement ps;
    Statement st;
    public ParkingDuration getParkingDuration(int duration_id){
        ParkingDuration parkingDuration = null;
        sql="SELECT * FROM parking_duration WHERE duration_id = ?";
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
        sql="SELECT * FROM parking_duration WHERE duration_value = ?";
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
        sql="SELECT duration_id FROM parking_duration order by duration_id DESC";
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
}

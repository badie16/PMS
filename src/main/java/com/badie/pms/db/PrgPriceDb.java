package com.badie.pms.db;


import com.badie.pms.model.ParkingPrice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrgPriceDb {
    Connection con = MyConnection.connection();

    PreparedStatement ps;
    Statement st;
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
//    public boolean exitParkingDurationValue(int duration_value){
//        sql="SELECT * FROM parking_duration WHERE duration_value = ?";
//        try {
//            ps=con.prepareStatement(sql);
//            ps.setInt(1, duration_value);
//            return  ps.executeQuery().next();
//        } catch (SQLException e) {
//            System.out.println("error " + e);
//        }
//        return false;
//    }

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

//    public boolean addParkingDuration(String duration_value){
//        String sql = "INSERT INTO `parking_duration` (`duration_value`) VALUE (?)";
//        try {
//            ps=con.prepareStatement(sql);
//            ps.setString(1, duration_value);
//            return ps.executeUpdate() != 0;
//        } catch (SQLException e) {
//            System.out.println("error " + e);
//        }
//        return false;
//    }
}

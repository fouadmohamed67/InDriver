package com.example.advSoft.Ride;

import com.example.advSoft.Ride.IRideDatabaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RideDatabaseConnect implements IRideDatabaseConnect {
    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }

    @Override
    public void set(JSONObject ride) throws SQLException, ClassNotFoundException {
         String query = " insert into ride (id,discount_value,discount_percent,price_after_discount) values (?,?,?,?)";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.setInt (1, (Integer) ride.get("id"));
        preparedStmt.setDouble (2, (Double) ride.get("discount_value"));
        preparedStmt.setDouble (3, (Double) ride.get("discount_percent"));
        preparedStmt.setDouble (4, (Double) ride.get("price_after_discount"));
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("The Ride is added successfully");

    }

    // TEST REQUIRED
    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String query = "delete from ride where id = '" + id + "'";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("The Ride is Deleted successfully");
    }

    @Override
    public JSONObject get(int id) throws SQLException, ClassNotFoundException {
        JSONObject ride = new JSONObject();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from ride where ( id ='"+id+"')");
        while(rs.next())
        {
            ride.put("id",rs.getInt("id"));
            String source= String.valueOf(rs.getTimestamp("arrive_source_time"));

            String destination= String.valueOf(rs.getTimestamp("arrive_destination_time"));


            ride.put("arrive_source_time", source);
            ride.put("arrive_destination_time",destination);
            ride.put("discount_value",rs.getDouble("discount_value"));
            ride.put("discount_percent",rs.getDouble("discount_percent"));
            ride.put("price_after_discount",rs.getDouble("price_after_discount"));
        }
         return ride;
    }

    @Override
    public JSONArray listAll() throws SQLException, ClassNotFoundException {
        JSONArray allRides = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from ride");
        while(rs.next())
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",rs.getInt("id"));
            jsonObject.put("arrive_source_time",rs.getDate("arrive_source_time"));
            jsonObject.put("arrive_destination_time",rs.getTimestamp("arrive_destination_time"));
            jsonObject.put("discount_value",rs.getDouble("discount_value"));
            jsonObject.put("discount_percent",rs.getDouble("discount_percent"));
            jsonObject.put("price_after_discount",rs.getDouble("price_after_discount"));
            allRides.put(jsonObject);
        }
        return allRides;
    }

    // TEST REQUIRED
    public JSONArray listAllByDriverName(String DriverName) throws SQLException, ClassNotFoundException {
        List<Integer> offerIds = new ArrayList<Integer>();
        JSONArray allRides = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from offer where ( DriverName= '" + DriverName + "' and accepted = 1)");
        while(rs.next())
        {
            offerIds.add(rs.getInt("offer_id"));
        }
        for(int offerId: offerIds)
        {
            Statement statement2 = establish_connection().createStatement();
            ResultSet rs2 = statement2.executeQuery("select *  from ride where ( offer_id= '" + offerId + "')");
            if(rs2.next())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",rs.getInt("id"));
                jsonObject.put("arrive_source_time",rs.getDate("arrive_source_time"));
                jsonObject.put("arrive_destination_time",rs.getTimestamp("arrive_destination_time"));
                jsonObject.put("discount_value",rs.getDouble("discount_value"));
                jsonObject.put("discount_percent",rs.getDouble("discount_percent"));
                jsonObject.put("price_after_discount",rs.getDouble("price_after_discount"));
                allRides.put(jsonObject);
            }
        }
        return allRides;
    }

    //TEST REQUIRED
    @Override
    public void update(JSONObject ride,int id) throws SQLException, ClassNotFoundException   {

            String   query ="";
            if(ride.get("arrive_destination_time").equals("null"))
            {
                    query = " update  ride SET id ="+id+", arrive_source_time='"+ride.get("arrive_source_time")+"' , discount_value='"+ride.get("discount_value")+"', discount_percent='"+ride.get("discount_percent")+"', price_after_discount='"+ride.get("price_after_discount")+"'   WHERE id = "+id+" ";

            }
            else
            {
                    query = " update  ride SET id ="+id+", arrive_source_time='"+ride.get("arrive_source_time")+"',arrive_destination_time='"+ride.get("arrive_destination_time")+"' , discount_value='"+ride.get("discount_value")+"', discount_percent='"+ride.get("discount_percent")+"', price_after_discount='"+ride.get("price_after_discount")+"'   WHERE id = "+id+" ";

            }
            PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
            preparedStmt.executeUpdate();
            establish_connection().close();
            System.out.println("The Ride updated successfully");


    }

    @Override
    public String listAllRides(String drivername) throws ClassNotFoundException, SQLException {
        Statement statement = establish_connection().createStatement();
        JSONArray allRides = new JSONArray();
        ResultSet rs = statement.executeQuery("select * from ride INNER JOIN  offer,requestedrides where( offer.driverName = '" + drivername + "')");
        while(rs.next())
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",rs.getInt("id"));
            jsonObject.put("driverName", rs.getInt("driverName"));
            jsonObject.put("clientName", rs.getInt("clientName"));
            jsonObject.put("clientNumber", rs.getInt("clientNumber"));
            jsonObject.put("arrive_source_time",rs.getDate("arrive_source_time"));
            jsonObject.put("arrive_destination_time",rs.getTimestamp("arrive_destination_time"));
            jsonObject.put("discount_value",rs.getDouble("discount_value"));
            jsonObject.put("discount_percent",rs.getDouble("discount_percent"));
            jsonObject.put("price_after_discount",rs.getDouble("price_after_discount"));
            allRides.put(jsonObject);
        }
        return allRides.toString();
    }

    @Override
    public boolean checkOpenedRide(String driverName) throws SQLException, ClassNotFoundException {
        Statement statement = establish_connection().createStatement();
        JSONArray allRides = new JSONArray();
        ResultSet rs = statement.executeQuery("select * from ride INNER JOIN  offer,requestedrides where(offer.driverName = '" + driverName + "' and ride.arrive_destination_time is NULL)");
        if(rs.next())
            return true;
        return false;
    }

    @Override
    public Integer getRequestID(int RideID) throws SQLException, ClassNotFoundException {
        Statement statement = establish_connection().createStatement();
        JSONArray allRides = new JSONArray();
        ResultSet rs = statement.executeQuery("select * from  requestedrides  INNER JOIN  ride,offer where ride.id="+RideID+" ");
        if(rs.next())
            return rs.getInt("requestedrides_id");
        else
            return -1;

    }

    @Override
    public String getOffersWithReqID(int ReqID) throws SQLException, ClassNotFoundException {
        JSONArray allRides = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select * from offer inner join requestedrides where offer.requestedrides_id="+ReqID+"");
        while(rs.next())
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("OfferID",rs.getInt("id"));
            jsonObject.put("driverName",rs.getString("driverName"));
            jsonObject.put("offerTime",rs.getTimestamp("offerTime"));
            jsonObject.put("price",rs.getDouble("price"));
            jsonObject.put("source",rs.getString("source"));
            jsonObject.put("destination",rs.getString("destination"));
            jsonObject.put("clientName",rs.getString("clientName"));
            allRides.put(jsonObject);
        }
        return allRides.toString();
    }
}

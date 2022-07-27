package com.example.advSoft.Offer;

import com.example.advSoft.Offer.IOfferDatabaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OfferDatabaseConnect implements IOfferDatabaseConnect {

    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }
    //TEST REQUIRED
    @Override
    public void set(JSONObject offer) throws SQLException, ClassNotFoundException {
        float price = BigDecimal.valueOf(offer.getDouble("price")).floatValue();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = " insert into offer (driverName, offerTime, price, requestedrides_id, accepted, accepted_time) values (?,?,?,?,?,?)";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.setString(1, (String) offer.get("driverName"));
        preparedStmt.setString(2, dtf.format(now).toString());
        preparedStmt.setFloat(3, price);
        preparedStmt.setInt (4, (Integer)offer.get("requestedrides_id"));
        preparedStmt.setInt(5, 0);
        preparedStmt.setString (6, null);
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("The Offer created successfully");
    }

    //TEST REQUIRED
    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        String query = "delete from offer where id = '" + id + "'";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("The Offer is Deleted successfully");
    }

    @Override
    public JSONObject get(int id) throws SQLException, ClassNotFoundException {
        JSONObject offer = new JSONObject();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from offer where  id = '"+ id + "'");
        while(rs.next())
        {
            offer.put("driverName",rs.getString("driverName"));
            offer.put("id",rs.getString("id"));
            offer.put("requestedrides_id",rs.getString("requestedrides_id"));
            offer.put("offerTime",rs.getString("offerTime"));
            offer.put("price",rs.getString("price"));
        }
        return offer;
    }

    @Override
    public JSONArray listAll() throws SQLException, ClassNotFoundException {
        JSONArray allOffers = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from offer");
        while(rs.next())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("driverName",rs.getString("driverName"));
                jsonObject.put("id",rs.getString("id"));
                jsonObject.put("requestedrides_id",rs.getString("requestedrides_id"));
                jsonObject.put("offerTime",rs.getString("offerTime"));
                jsonObject.put("price",rs.getString("price"));
                allOffers.put(jsonObject);
            }

        return allOffers;
    }

    //not Sure if the update query work
    @Override
    public void update(JSONObject offer,int id) throws SQLException, ClassNotFoundException {

         Integer booll=(Integer)offer.get("accepted");
         String query = "UPDATE offer SET driverName ='"+offer.get("driverName")+"'" + ", offerTime ='"+offer.get("offerTime")+"'" + ", price ='"+offer.get("price")+"'" + ", requestedrides_id ='"+offer.get("requestedrides_id")+"'" + ", accepted ="+ booll + ", accepted_time ='" +offer.get("accepted_time")+"' where id="+id+" ";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("one offer updated");
    }

    @Override
    public String viewOffersOfClient(String clientName) throws SQLException, ClassNotFoundException {

        JSONArray offers = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT *  FROM requestedrides INNER JOIN offer WHERE requestedrides.clientName = '" + clientName + "' and offer.accepted = 0");
        while(rs.next())
        {
            JSONObject offer = new JSONObject();
            offer.put("id",rs.getString("offer.id"));
            offer.put("source",rs.getString("source"));
            offer.put("destination",rs.getString("destination"));
            offer.put("clients_number",rs.getString("clients_number"));
            offer.put("clientName",rs.getString("clientName"));
            offer.put("driverName",rs.getString("driverName"));
            offer.put("price",rs.getString("price"));
            offer.put("offerTime",rs.getString("offerTime"));
            offers.put(offer);
        }
        return offers.toString();
    }

}

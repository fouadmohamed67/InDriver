package com.example.advSoft.Rating;

import com.example.advSoft.connection.DataBaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.*;

public class RatingDatabaseConnect implements DataBaseConnect {
    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }
    @Override
    public void set(JSONObject rate) throws SQLException, ClassNotFoundException {
        float rate_float = BigDecimal.valueOf(rate.getDouble("rate")).floatValue();
        String query = " insert into rate (id,rate) values (?,?)";
        PreparedStatement preparedStmt = establish_connection().prepareStatement(query);
        preparedStmt.setInt(1, (Integer) rate.get("id"));
        preparedStmt.setFloat (2, rate_float);
        preparedStmt.executeUpdate();
        establish_connection().close();
        System.out.println("one rate added");
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public JSONObject get(int id) throws SQLException, ClassNotFoundException {
        JSONObject rate = new JSONObject();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select *  from offer where  id = '"+ id + "'");
        while(rs.next())
        {
            rate.put("clientName",rs.getString("clientName"));
            rate.put("driverName",rs.getString("driverName"));
            rate.put("rate",rs.getFloat("rate"));
        }
        return rate;
    }

    @Override
    public JSONArray listAll() throws SQLException, ClassNotFoundException {
        JSONArray allRates = new JSONArray();
        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select * from  rate INNER JOIN ride,offer,requestedrides");
        while(rs.next())
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clientName",rs.getString("clientName"));
            jsonObject.put("rate",rs.getString("rate"));
            jsonObject.put("driverName",rs.getString("driverName"));
             allRates.put(jsonObject);
        }

        return allRates;
    }

    @Override
    public void update(JSONObject temp, int id) throws SQLException, ClassNotFoundException {

    }
}

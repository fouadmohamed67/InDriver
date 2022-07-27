package com.example.advSoft.Discount.NumOfClientsDiscount;

import com.example.advSoft.Discount.IDiscountDatabaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class NClientDatabaseConnect implements IDiscountDatabaseConnect {
    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }

    @Override
    public void set(JSONObject object) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public JSONObject get(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JSONArray listAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void update(JSONObject temp, int id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException {

        Statement statement = establish_connection().createStatement();
        ResultSet rs = statement.executeQuery("select clients_number from requestedrides where clientName='"+clientName+"' and accepted = 0");
        if(rs.next())
        {
            if(rs.getInt("clients_number") >= 2)
            {
                System.out.println("discount  5% from NClients");
                return 0.05;
            }

            else
                return 0;
        }
        return 0;
    }
}

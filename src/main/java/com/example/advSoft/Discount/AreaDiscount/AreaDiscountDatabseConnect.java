package com.example.advSoft.Discount.AreaDiscount;

import com.example.advSoft.Discount.IDiscountDatabaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class AreaDiscountDatabseConnect implements IDiscountDatabaseConnect {
    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }

    @Override
    public void set(JSONObject object) throws SQLException, ClassNotFoundException {



                Statement statement = establish_connection().createStatement();
                String query = " insert into DiscountAreas (area) values (?)";
                PreparedStatement preparedStmt =establish_connection().prepareStatement(query);
                preparedStmt.setString (1, (String) object.get("area"));
                preparedStmt.executeUpdate();

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
        ResultSet rs = statement.executeQuery("select destination from requestedrides where clientName='"+clientName+"' and accepted = 0");
       if(rs.next())
       {
            statement = establish_connection().createStatement();
           ResultSet rs2 = statement.executeQuery("select *  from discountareas where area='"+rs.getString("destination")+"'");
           if(rs2.next())
           {
               System.out.println("discount 10% from oarea ");
               return 0.1;
           }
       }
        return 0;
    }




}

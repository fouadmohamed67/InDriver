package com.example.advSoft.Discount.BirthdayDiscount;

import com.example.advSoft.Discount.IDiscountDatabaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.time.LocalDate;

public class BirthdayDatabaseConnect implements IDiscountDatabaseConnect {
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
                LocalDate localDate = LocalDate.now();
                int day = localDate.getDayOfMonth();
                int month = localDate.getMonthValue();
                Statement statement = establish_connection().createStatement();
                ResultSet rs = statement.executeQuery("select month(birthdate) as month, day(birthdate) as day from client where username='"+clientName+"'");
               if(rs.next())
                {

                    if(month == rs.getInt("month") && day == rs.getInt("day"))
                    {
                        System.out.println("discount 10% from Birth");
                         return 0.1;
                    }

                }
                return 0;
    }
}
